package io.billie.currency.resource

import io.billie.currency.model.Currency
import io.billie.currency.service.CurrencyService
import io.swagger.v3.oas.annotations.media.ArraySchema
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("currencies", produces=["application/json; charset=UTF-8"])
class CurrencyResource(val service: CurrencyService) {

    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "All currencies",
                content = [
                    (Content(
                        mediaType = "application/json",
                        array = (ArraySchema(schema = Schema(implementation = Currency::class)))
                    ))]
            )]
    )
    @GetMapping
    fun index(): List<Currency> = service.listCurrencies()
}
