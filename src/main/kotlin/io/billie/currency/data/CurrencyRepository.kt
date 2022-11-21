package io.billie.currency.data

import io.billie.currency.model.Currency
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import java.sql.ResultSet


@Repository
class CurrencyRepository {

    @Autowired
    lateinit var jdbcTemplate: JdbcTemplate


    @Transactional(readOnly = true)
    fun getAllCurrencies(): List<Currency> {
        return jdbcTemplate.query("SELECT * from organisations_schema.currencies", currencyMapper())
    }

    @Transactional(readOnly = true)
    fun findCurrencyByIsoCode(isoCode: String): Currency? {
        return try {
            jdbcTemplate.queryForObject(
                "SELECT * from organisations_schema.currencies where code = ?",
                currencyMapper(),
                isoCode
            )
        } catch (e: EmptyResultDataAccessException) {
            return null
        }
    }


    private fun currencyMapper() = RowMapper<Currency> { it: ResultSet, _: Int ->
        Currency(
            it.getString("name"),
            it.getString("code"),
            it.getString("symbol"),
        )
    }

}
