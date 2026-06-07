package com.project.tickr.core.result

sealed class AppError(open val message: String) {
    data object Network : AppError("Network unavailable")
    data object Unauthorized : AppError("Unauthorized / session invalid")
    data class NotFound(val entity: String) : AppError("$entity not found")
    data class Serialization(val detail: String) : AppError("Parse error: $detail")
    data class Unknown(val detail: String) : AppError(detail)
}
