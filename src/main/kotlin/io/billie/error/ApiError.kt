package io.billie.error

sealed class ApiError(val errors: List<String>): RuntimeException() {
    data class ValidationError(val e: List<String>): ApiError(e)
    data class SystemError(val e: List<String>): ApiError(e)
}
