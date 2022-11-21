package io.billie.invoices.data

import io.billie.invoices.fixtures.InvoiceFixtures
import io.billie.invoices.viewmodel.Invoice
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import java.sql.ResultSet
import java.util.*

@SpringBootTest
internal class InvoiceRepositoryTest {

    @Autowired
    private lateinit var repository: InvoiceRepository

    @Autowired
    private lateinit var jdbcTemplate: JdbcTemplate

    private lateinit var contactDetailsId: UUID
    private lateinit var organisationId: UUID
    private lateinit var invoiceId: UUID
    private lateinit var invoiceNoteId: UUID
    private lateinit var invoiceItemId: UUID

    @BeforeEach
    fun setUp() {

        contactDetailsId = jdbcTemplate.query(
            """
            INSERT INTO organisations_schema.contact_details(phone_number, fax, email)
            VALUES ('2693193', '2626481', 'hello@test.com')
            RETURNING id;
        """.trimIndent(), idMapper()
        ).first()!!

        organisationId = jdbcTemplate.query(
            """
            INSERT INTO organisations_schema.organisations(name, date_founded, country_code, vat_number, registration_number,
                                                           legal_entity_type, contact_details_id)
            VALUES ('test',
                    now(),
                    'DE',
                    '123',
                    '123',
                    'NONPROFIT_ORGANIZATION',
                    ?)
            RETURNING id;
                    """.trimIndent(), idMapper(), contactDetailsId
        ).first()!!

        invoiceId = jdbcTemplate.query(
            """
                INSERT INTO organisations_schema.invoice(organisation_id, created_date, due_date, currency_iso)
                VALUES (?, now(), now() + interval '7' day, 'EUR')
                RETURNING id;
                    """.trimIndent(), idMapper(), organisationId
        ).first()!!

        invoiceNoteId = jdbcTemplate.query(
            """
                INSERT INTO organisations_schema.invoice_note(invoice_id, description)
                VALUES (?, 'desc test')
                RETURNING id;
                    """.trimIndent(), idMapper(), invoiceId
        ).first()!!

        invoiceItemId = jdbcTemplate.query(
            """
                INSERT INTO organisations_schema.invoice_item (invoice_id, description, quantity, cost_per_unit)
                VALUES (?, 'desc test', 2, 30.0)
                RETURNING id;
                    """.trimIndent(), idMapper(), invoiceId
        ).first()!!
    }

    @AfterEach
    fun tearDown() {
        jdbcTemplate.update("DELETE FROM organisations_schema.contact_details WHERE id = ?", contactDetailsId)
        jdbcTemplate.update("DELETE FROM organisations_schema.organisations WHERE id = ?", organisationId)
        jdbcTemplate.update("DELETE FROM organisations_schema.invoice_note WHERE id = ?", invoiceNoteId)
        jdbcTemplate.update("DELETE FROM organisations_schema.invoice_item WHERE id = ?", invoiceItemId)
        jdbcTemplate.update("DELETE FROM organisations_schema.invoice WHERE id = ?", invoiceId)
    }


    @Test
    fun testGetAllInvoices() {
        val result = repository.getAllInvoices()
        assertEquals(
            invoiceId.toString(),
            result.first { invoice: Invoice -> UUID.fromString(invoice.invoice_id!!) == invoiceId }.invoice_id,
            "getAllInvoices should return the invoice inserted"
        )
    }

    @Test
    fun testCreateInvoice() {
        val newDesc = UUID.randomUUID().toString()
        val invoice = InvoiceFixtures.mockInvoices.first().copy(
            invoice_id = null,
            invoiceItems = InvoiceFixtures.mockInvoices.first().invoiceItems + InvoiceFixtures.mockInvoices.first().invoiceItems.first()
                .copy(
                    description = newDesc
                )
        )

        val result = repository.create(invoice)
        val expectedId = jdbcTemplate.query(
            "SELECT invoice_id from organisations_schema.invoice_item WHERE description = ?",
            invoiceIdMapper(),
            newDesc
        ).first()

        assertEquals(expectedId, result)

        jdbcTemplate.update("DELETE FROM organisations_schema.invoice_note WHERE invoice_id = ?", expectedId)
        jdbcTemplate.update("DELETE FROM organisations_schema.invoice_item WHERE invoice_id = ?", expectedId)
        jdbcTemplate.update("DELETE FROM organisations_schema.invoice WHERE id = ?", expectedId)
    }

    private fun idMapper() = RowMapper<UUID> { it: ResultSet, _: Int ->
        UUID.fromString(it.getString("id"))
    }

    private fun invoiceIdMapper() = RowMapper<UUID> { it: ResultSet, _: Int ->
        UUID.fromString(it.getString("invoice_id"))
    }
}