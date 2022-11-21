package io.billie.invoices.data

import io.billie.invoices.viewmodel.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.support.GeneratedKeyHolder
import org.springframework.jdbc.support.KeyHolder
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import java.sql.Date
import java.sql.ResultSet
import java.util.*


@Repository
class InvoiceRepository {
    @Autowired
    lateinit var jdbcTemplate: JdbcTemplate

    @Transactional(readOnly = true)
    fun getAllInvoices(): List<Invoice> {
        return listAllInvoices()
    }

    @Transactional
    fun create(organisation: Invoice): UUID {
        return createInvoice(organisation)
    }

    private fun createInvoice(invoice: Invoice): UUID {
        val keyHolder: KeyHolder = GeneratedKeyHolder()
        jdbcTemplate.update(
            { connection ->
                val ps = connection.prepareStatement(
                    "INSERT INTO organisations_schema.invoice(organisation_id, created_date, due_date, currency_iso) VALUES (?, ?, ?, ?);",
                    arrayOf("id")
                )
                ps.setObject(1, UUID.fromString(invoice.organisationId))
                ps.setDate(2, Date.valueOf(invoice.dateCreated))
                ps.setDate(3, Date.valueOf(invoice.dueDate))
                ps.setString(4, invoice.currency)
                ps
            }, keyHolder
        )

        val invoiceId = keyHolder.getKeyAs(UUID::class.java)!!

        createInvoiceNotes(invoiceId, invoice.notes)
        createInvoiceItems(invoiceId, invoice.invoiceItems)

        return invoiceId
    }

    private fun createInvoiceNotes(invoice_id: UUID, notes: List<String>) {
        notes.forEach {
            jdbcTemplate.update { connection ->
                val ps = connection.prepareStatement(
                    "INSERT INTO organisations_schema.invoice_note(invoice_id, description) VALUES (?, ?) ON CONFLICT DO NOTHING",
                    arrayOf("id")
                )
                ps.setObject(1, invoice_id)
                ps.setString(2, it)
                ps
            }
        }
    }

    private fun createInvoiceItems(invoice_id: UUID, items: List<InvoiceLineItem>) {
        items.forEach {
            jdbcTemplate.update { connection ->
                val ps = connection.prepareStatement(
                    "INSERT INTO organisations_schema.invoice_item (invoice_id, description, quantity, cost_per_unit, discount) VALUES (?, ?, ?, ?, ?) ON CONFLICT DO NOTHING",
                    arrayOf("id")
                )
                ps.setObject(1, invoice_id)
                ps.setString(2, it.description)
                ps.setDouble(3, it.quantity)
                ps.setBigDecimal(4, it.costPerUnit)
                ps.setBigDecimal(5, it.discount)
                ps
            }
        }
    }

    private fun listAllInvoices(): List<Invoice> {
        val invoices = jdbcTemplate.query("SELECT id, organisation_id, created_date, due_date, currency_iso from organisations_schema.invoice", invoiceMapper())
        return invoices.filterNotNull().map{
            val items = when {
                it.invoice_id != null -> jdbcTemplate.query("SELECT description, quantity, cost_per_unit, discount FROM organisations_schema.invoice_item WHERE invoice_id = ?", invoiceItemMapper(), UUID.fromString(it.invoice_id))
                else -> emptyList()
            }
            val notes =  when {
                it.invoice_id != null -> jdbcTemplate.queryForList("SELECT description FROM organisations_schema.invoice_note WHERE invoice_id = ?", String::class.java, UUID.fromString(it.invoice_id))
                else -> emptyList()
            }
            it.copy(invoiceItems = items.toList(), notes = notes)
        }
    }
    private fun invoiceMapper() = RowMapper<Invoice> { it: ResultSet, _: Int ->

        val id = it.getString(1)
        val orgId = it.getString(2)
        val createdDate = it.getDate(3).toLocalDate()
        val dueDate = it.getDate(4).toLocalDate()
        val currency = it.getString(5)

        Invoice(
            orgId,
            id,
            createdDate,
            dueDate,
            currency,
            emptyList(),
            emptyList()
        )
    }

    private fun invoiceItemMapper() = RowMapper<InvoiceLineItem> { it: ResultSet, _: Int ->
        val description = it.getString(1)
        val quantity = it.getDouble(2)
        val costPerUnit = it.getBigDecimal(3)
        val discount = it.getBigDecimal(4)

        InvoiceLineItem(
            description,
            quantity,
            costPerUnit,
            discount
        )
    }

}
