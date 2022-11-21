package io.billie.invoices.viewmodel

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDate
import java.util.*
import javax.validation.constraints.NotBlank

@Table("ORGANISATIONS")
data class Invoice(
    @field:NotBlank @JsonProperty("organisation_id") val organisationId: String,
    @JsonProperty("invoice_id") val invoice_id: String?,
    @JsonFormat(pattern = "dd/MM/yyyy") @JsonProperty("date_created") val dateCreated: LocalDate,
    @JsonFormat(pattern = "dd/MM/yyyy") @JsonProperty("due_date") val dueDate: LocalDate,
    @JsonProperty("currency") val currency: String,
    @JsonProperty("items") val invoiceItems: List<InvoiceLineItem>,
    @JsonProperty("notes") val notes: List<String>,
)
