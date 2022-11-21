package io.billie.currency.service

import io.billie.currency.data.CurrencyRepository
import io.billie.currency.model.Currency
import org.springframework.stereotype.Service

@Service
class CurrencyService(val db: CurrencyRepository) {
    fun listCurrencies(): List<Currency> = db.getAllCurrencies()
}
