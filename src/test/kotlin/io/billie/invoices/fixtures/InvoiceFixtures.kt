package io.billie.invoices.fixtures

import io.billie.invoices.viewmodel.Invoice
import io.billie.invoices.viewmodel.InvoiceLineItem
import java.math.BigDecimal
import java.time.LocalDate
import java.util.UUID

internal object InvoiceFixtures {


    private val testingId1 = UUID.randomUUID().toString()
    private val testingId2 = UUID.randomUUID().toString()
    private val testingDate = LocalDate.now()

    val mockInvoices =
        listOf(
            Invoice(
                organisationId = testingId1,
                invoice_id = testingId2,
                dateCreated = testingDate.minusDays(1),
                dueDate = testingDate.plusDays(1),
                currency = "AUD",
                invoiceItems = listOf(
                    InvoiceLineItem("line item 1", 2.0, BigDecimal.valueOf(420.69), null),
                    InvoiceLineItem("line item 2", 3.0, BigDecimal.valueOf(1337.0), BigDecimal.valueOf(666.0))
                ),
                notes = listOf("note a", "note b", "note c")
            ),
            Invoice(
                organisationId = testingId2,
                invoice_id = testingId1,
                dateCreated = testingDate.minusDays(1),
                dueDate = testingDate.plusDays(1),
                currency = "EUR",
                invoiceItems = listOf(
                    InvoiceLineItem("line item 1", 2.0, BigDecimal.valueOf(123.45), BigDecimal.valueOf(1.0)),
                    InvoiceLineItem("line item 2", 3.0, BigDecimal.valueOf(1337.0), BigDecimal.valueOf(666.0)),
                    InvoiceLineItem("line item 2", 3.0, BigDecimal.valueOf(1.0), null)
                ),
                notes = listOf("note a", "note b", "note c")
            )
        )

    fun mockInvoices(id1: String, id2: String, date: LocalDate): List<Invoice> {
        return listOf(
            Invoice(
                organisationId = id1,
                invoice_id = id2,
                dateCreated = date.minusDays(1),
                dueDate = date.plusDays(1),
                currency = "AUD",
                invoiceItems = listOf(
                    InvoiceLineItem("line item 1", 2.0, BigDecimal.valueOf(420.69), null),
                    InvoiceLineItem("line item 2", 3.0, BigDecimal.valueOf(1337.0), BigDecimal.valueOf(666.0))
                ),
                notes = listOf("note a", "note b", "note c")
            ),
            Invoice(
                organisationId = id2,
                invoice_id = id1,
                dateCreated = date.minusDays(1),
                dueDate = date.plusDays(1),
                currency = "EUR",
                invoiceItems = listOf(
                    InvoiceLineItem("line item 1", 2.0, BigDecimal.valueOf(123.45), BigDecimal.valueOf(1.0)),
                    InvoiceLineItem("line item 2", 3.0, BigDecimal.valueOf(1337.0), BigDecimal.valueOf(666.0)),
                    InvoiceLineItem("line item 2", 3.0, BigDecimal.valueOf(1.0), null)
                ),
                notes = listOf("note a", "note b", "note c")
            )
        )
    }
}