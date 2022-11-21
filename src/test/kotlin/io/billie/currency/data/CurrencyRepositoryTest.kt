package io.billie.currency.data

import io.billie.currency.model.Currency
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
internal class CurrencyRepositoryTest {

    @Autowired
    private lateinit var repository: CurrencyRepository

    @Test
    fun testGetAllCurrencies() {
        val result = repository.getAllCurrencies()
        assertEquals(true, result.isNotEmpty(), "getAllCurrencies should return the currencies")
    }

    @Test
    fun testFindCurrencyByIsoCodeWithAValidISOCode() {
        val result = repository.findCurrencyByIsoCode("EUR")
        assertEquals(Currency("Euro", "EUR", "€"), result)
    }

    @Test
    fun testFindCurrencyByIsoCodeWithAInvalidISOCode() {
        val result = repository.findCurrencyByIsoCode("EURO")
        assertNull(result)
    }
}