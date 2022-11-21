package io.billie.invoices.service

import io.billie.currency.data.CurrencyRepository
import io.billie.error.ApiError
import io.billie.invoices.data.InvoiceRepository
import io.billie.invoices.viewmodel.Invoice
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class InvoiceService(val invoiceRepository: InvoiceRepository, val currencyRepository: CurrencyRepository) {

    @Transactional(readOnly = true)
    fun findInvoices(): List<Invoice> = invoiceRepository.getAllInvoices()

    @Transactional
    fun createInvoice(invoice: Invoice): UUID {
        return when (val error = validateRequest(invoice)) {
            null -> invoiceRepository.create(invoice)
            else -> throw error
        }
    }

    private fun validateRequest(invoice: Invoice): ApiError? {
        val errors = mutableListOf<String>()
        if(currencyRepository.findCurrencyByIsoCode(invoice.currency) == null) {
            errors.add("Invalid Currency ${invoice.currency}!")
        }
        if(invoice.invoiceItems.isEmpty()) {
            errors.add("An invoice must contain items!")
        }
        if(invoice.dueDate.isBefore(invoice.dateCreated)) {
            errors.add("An invoice due date cannot be before the issue date!")
        }
        invoice.invoiceItems.forEachIndexed { index, item ->
            if(item.description.isBlank()) {
                errors.add("Line Item No: ${index + 1} is missing a description!")
            }
            if(item.quantity < 1f) {
                errors.add("Line Item No: ${index + 1} quantity must be at least one!")
            }
            if(item.discount != null && item.discount > item.costPerUnit) {
                errors.add("Line Item No: ${index + 1} discount cannot larger than the cost of the unit!")
            }
        }
        return when {
            errors.isNotEmpty() -> ApiError.ValidationError(errors)
            else -> null
        }
    }

}
