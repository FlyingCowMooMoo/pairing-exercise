package io.billie.invoices.viewmodel

import com.fasterxml.jackson.annotation.JsonProperty
import java.math.BigDecimal

data class InvoiceLineItem(
    val description: String,
    val quantity: Double,
    @JsonProperty("cost_per_unit") val costPerUnit: BigDecimal,
    val discount: BigDecimal?
)
