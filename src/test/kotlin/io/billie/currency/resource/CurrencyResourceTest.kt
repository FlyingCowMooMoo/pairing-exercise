package io.billie.currency.resource

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import io.billie.currency.model.Currency
import io.billie.currency.service.CurrencyService
import io.billie.invoices.service.InvoiceService
import io.billie.invoices.viewmodel.Invoice
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.whenever
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(MockitoExtension::class)
internal class CurrencyResourceTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var mapper: ObjectMapper

    @MockBean
    private lateinit var service: CurrencyService

    private val mockCurrencies = listOf(
        Currency("Euro", "EUR", "€"),
        Currency("Dollars", "AUD", "$")
    )

    @Test
    fun testListCurrencies() {
        whenever(service.listCurrencies()).thenReturn(mockCurrencies)

        val response = mockMvc.perform(
            MockMvcRequestBuilders.get("/currencies")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().is2xxSuccessful)
            .andReturn()

        val parsedCurrencies = mapper.readValue<List<Currency>>(response.response.contentAsString)

        assertEquals(mockCurrencies, parsedCurrencies)

    }
}