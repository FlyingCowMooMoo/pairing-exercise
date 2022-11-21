package io.billie.currency.model

import org.springframework.data.relational.core.mapping.Table


@Table("CURRENCIES")
data class Currency(val name: String, val code: String, val symbol: String)
