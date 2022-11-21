package io.billie.invoices.resource

import io.billie.error.ApiError
import io.billie.invoices.service.InvoiceService
import io.billie.invoices.viewmodel.*
import io.swagger.v3.oas.annotations.media.ArraySchema
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.validation.Valid


@RestController
@RequestMapping("invoices")
class InvoiceResource(val service: InvoiceService) {

    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "All invoices",
                content = [
                    (Content(
                        mediaType = "application/json",
                        array = (ArraySchema(schema = Schema(implementation = Invoice::class)))
                    ))]
            )]
    )
    @GetMapping
    fun index(): List<Invoice> {
        return service.findInvoices()
    }

    @PostMapping
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "Accepted the new invoice",
                content = [
                    (Content(
                        mediaType = "application/json",
                        array = (ArraySchema(schema = Schema(implementation = Entity::class)))
                    ))]
            ),
            ApiResponse(
                responseCode = "400", description = "Bad request", content = [Content(
                    mediaType = "application/json",
                    array = (ArraySchema(schema = Schema(implementation = Error::class)))
                )]
            ),
            ApiResponse(
                responseCode = "400", description = "Bad payload", content = [Content(
                    mediaType = "application/json",
                    array = (ArraySchema(schema = Schema(implementation = InvoiceResourceError::class)))
                )]
            )]
    )
    fun post(@Valid @RequestBody invoice: Invoice): ResponseEntity<InvoiceResourceResponse> {
        return try {
            if(invoice.invoice_id != null) {
                throw ApiError.SystemError(listOf("Updating Invoices is not implemented!"))
            }
            val id = service.createInvoice(invoice)
            ResponseEntity(Entity(id), HttpStatus.OK)
        } catch (e: ApiError) {
            ResponseEntity(InvoiceResourceError(e.errors), HttpStatus.BAD_REQUEST)
        }
    }

}
