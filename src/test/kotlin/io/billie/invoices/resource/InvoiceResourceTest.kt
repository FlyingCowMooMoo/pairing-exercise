package io.billie.invoices.resource

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import io.billie.error.ApiError
import io.billie.invoices.fixtures.InvoiceFixtures
import io.billie.invoices.service.InvoiceService
import io.billie.invoices.viewmodel.Invoice
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.any
import org.mockito.kotlin.whenever
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import java.util.*

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(MockitoExtension::class)
internal class InvoiceResourceTest {

    private val mockInvoices = InvoiceFixtures.mockInvoices

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var mapper: ObjectMapper

    @MockBean
    private lateinit var invoiceService: InvoiceService


    @BeforeEach
    fun setUp() {
        whenever(invoiceService.findInvoices()).thenReturn(mockInvoices)
        whenever(invoiceService.createInvoice(any())).thenReturn(UUID.fromString(mockInvoices.first().invoice_id))
    }

    @Test
    fun testIndexSerializesResponsesCorrectly() {
        val response = mockMvc.perform(
            MockMvcRequestBuilders.get("/invoices")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().is2xxSuccessful)
            .andReturn()

        val parsedInvoices = mapper.readValue<List<Invoice>>(response.response.contentAsString)

        assertEquals(mockInvoices, parsedInvoices)
    }

    @Test
    fun testPostWhenTheServiceSucceeds() {
        val invoiceJson = mapper.writeValueAsString(mockInvoices.first().copy(invoice_id = null))

        mockMvc.perform(
            MockMvcRequestBuilders.post("/invoices")
                .contentType(MediaType.APPLICATION_JSON)
                .content(invoiceJson)
        ).andExpect(MockMvcResultMatchers.status().is2xxSuccessful)
            .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(mockInvoices.first().invoice_id))
    }

    @Test
    fun testPostWhenTheServiceFailsWithApiError() {
        val invoiceJson = mapper.writeValueAsString(mockInvoices.first().copy(invoice_id = null))

        whenever(invoiceService.createInvoice(any())).thenThrow(ApiError.ValidationError(listOf("err1", "err2")))

        mockMvc.perform(
            MockMvcRequestBuilders.post("/invoices")
                .contentType(MediaType.APPLICATION_JSON)
                .content(invoiceJson)
        ).andExpect(MockMvcResultMatchers.status().is4xxClientError)
            .andExpect(MockMvcResultMatchers.jsonPath("$.errors.[0]").value("err1"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.errors.[1]").value("err2"))
    }

    @Test
    fun testPostWhenTheServiceFailsWithApiErrorAsUpdateIsNotImplemented() {
        val invoiceJson = mapper.writeValueAsString(mockInvoices.first())

        mockMvc.perform(
            MockMvcRequestBuilders.post("/invoices")
                .contentType(MediaType.APPLICATION_JSON)
                .content(invoiceJson)
        ).andExpect(MockMvcResultMatchers.status().is4xxClientError)
            .andExpect(MockMvcResultMatchers.jsonPath("$.errors.[0]").value("Updating Invoices is not implemented!"))
    }

    @Test
    fun testPostWhenWithAnInvalidPayload() {
        val json = mapper.writeValueAsString(
            "{\n" +
                    "\tcolor: \"red\",\n" +
                    "\tvalue: \"#f00\"\n" +
                    "}"
        )

        whenever(invoiceService.createInvoice(any())).thenThrow(ApiError.ValidationError(listOf("err1", "err2")))

        mockMvc.perform(
            MockMvcRequestBuilders.post("/invoices")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
        ).andExpect(MockMvcResultMatchers.status().is4xxClientError)
    }
}