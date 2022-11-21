package io.billie.invoices.viewmodel

import org.springframework.data.relational.core.mapping.Table
import java.util.*

@Table("ORGANISATIONS")
data class InvoiceResponse(val id: UUID, val errors: List<String>)
