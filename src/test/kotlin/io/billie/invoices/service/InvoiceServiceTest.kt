package io.billie.invoices.service

import io.billie.currency.data.CurrencyRepository
import io.billie.currency.model.Currency
import io.billie.error.ApiError
import io.billie.invoices.data.InvoiceRepository
import io.billie.invoices.fixtures.InvoiceFixtures
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import java.math.BigDecimal
import java.time.LocalDate
import java.util.*

internal class InvoiceServiceTest {

    private lateinit var invoiceRepository: InvoiceRepository

    private lateinit var currencyRepository: CurrencyRepository

    private lateinit var invoiceService: InvoiceService

    @BeforeEach
    fun setUp() {
        currencyRepository = mock()
        invoiceRepository = mock()

        invoiceService = InvoiceService(invoiceRepository, currencyRepository)

        whenever(currencyRepository.findCurrencyByIsoCode("EUR")).thenReturn(Currency("Euro", "EUR", "€"))
        whenever(currencyRepository.findCurrencyByIsoCode("AUD")).thenReturn(Currency("Dollars", "AUD", "$"))
        whenever(invoiceRepository.getAllInvoices()).thenReturn(InvoiceFixtures.mockInvoices)
    }

    @Test
    fun testFindInvoices() {
        // Not really a useful test at this point, however one can assume more business logic will make it's way into here
        assertEquals(InvoiceFixtures.mockInvoices, invoiceService.findInvoices())
    }

    @Test
    fun createInvoiceWithValidInvoice() {
        val newId = UUID.randomUUID()
        val invoice = InvoiceFixtures.mockInvoices.first().copy(invoice_id = null)
        whenever(invoiceRepository.create(invoice)).thenReturn(newId)
        val result = invoiceService.createInvoice(invoice)
        assertEquals(result, newId)
    }

    @Test
    fun createInvoiceWithInvalidCurrency() {
        val invoice = InvoiceFixtures.mockInvoices.first().copy(invoice_id = null, currency = "LOL")
        val f: () -> Unit = { invoiceService.createInvoice(invoice) }
        val error = assertThrows<ApiError>("Should fail as the currency is invalid", f)
        assertEquals(listOf(("Invalid Currency LOL!")), error.errors)
    }

    @Test
    fun createInvoiceWithNoLineItems() {
        val invoice = InvoiceFixtures.mockInvoices.first().copy(invoice_id = null, invoiceItems = emptyList())
        val f: () -> Unit = { invoiceService.createInvoice(invoice) }
        val error = assertThrows<ApiError>("Should fail as the invoice contains no line items", f)
        assertEquals(listOf(("An invoice must contain items!")), error.errors)
    }

    @Test
    fun createInvoiceWithDueDateBeforeIssueDate() {
        val invoice =
            InvoiceFixtures.mockInvoices.first().copy(invoice_id = null, dueDate = LocalDate.now().minusDays(7))
        val f: () -> Unit = { invoiceService.createInvoice(invoice) }
        val error = assertThrows<ApiError>("Should fail as the invoice contains a due date before the issue date", f)
        assertEquals(listOf(("An invoice due date cannot be before the issue date!")), error.errors)
    }

    @Test
    fun createInvoiceWithALLineItemContainingAnInvalidDescription() {
        val invoiceItems = InvoiceFixtures.mockInvoices.first().invoiceItems.mapIndexed { index, invoiceLineItem ->
            when {
                index % 2 == 0 -> return@mapIndexed invoiceLineItem.copy(description = "")
                else -> return@mapIndexed invoiceLineItem
            }
        }
        val invoice = InvoiceFixtures.mockInvoices.first().copy(invoice_id = null, invoiceItems = invoiceItems)
        val f: () -> Unit = { invoiceService.createInvoice(invoice) }
        val error =
            assertThrows<ApiError>("Should fail as the invoice contains a line item with a blank description", f)
        assertEquals(listOf(("Line Item No: 1 is missing a description!")), error.errors)
    }

    @Test
    fun createInvoiceWithALLineItemContainingAnInvalidQuantity() {
        val invoiceItems = InvoiceFixtures.mockInvoices.first().invoiceItems.mapIndexed { index, invoiceLineItem ->
            when {
                index % 2 != 0 -> return@mapIndexed invoiceLineItem.copy(quantity = 0.0)
                else -> return@mapIndexed invoiceLineItem
            }
        }
        val invoice = InvoiceFixtures.mockInvoices.first().copy(invoice_id = null, invoiceItems = invoiceItems)
        val f: () -> Unit = { invoiceService.createInvoice(invoice) }
        val error =
            assertThrows<ApiError>("Should fail as the invoice contains a line item with an invalid quantity", f)
        assertEquals(listOf(("Line Item No: 2 quantity must be at least one!")), error.errors)
    }

    @Test
    fun createInvoiceWithALLineItemContainingAnInvalidDiscount() {
        val invoiceItems = InvoiceFixtures.mockInvoices.first().invoiceItems.mapIndexed { index, invoiceLineItem ->
            when {
                index % 2 != 0 -> return@mapIndexed invoiceLineItem.copy(
                    discount = invoiceLineItem.costPerUnit + BigDecimal.valueOf(
                        33
                    )
                )

                else -> return@mapIndexed invoiceLineItem
            }
        }
        val invoice = InvoiceFixtures.mockInvoices.first().copy(invoice_id = null, invoiceItems = invoiceItems)
        val f: () -> Unit = { invoiceService.createInvoice(invoice) }
        val error =
            assertThrows<ApiError>("Should fail as the invoice contains a line item with an invalid discount", f)
        assertEquals(listOf(("Line Item No: 2 discount cannot larger than the cost of the unit!")), error.errors)
    }


    @Test
    fun createInvoiceWithInvalidFields() {
        val invoice =
            InvoiceFixtures.mockInvoices.first().copy(invoice_id = null, currency = "LOL", invoiceItems = emptyList())
        val f: () -> Unit = { invoiceService.createInvoice(invoice) }
        val error = assertThrows<ApiError>("Should fail invoice contains an invalid currency and no line items", f)
        assertEquals(listOf("Invalid Currency LOL!", "An invoice must contain items!"), error.errors)
    }

}