package com.project.tickr.core.validation

sealed interface ValidationResult {
    data object Valid : ValidationResult
    data class Invalid(val field: String, val reason: String) : ValidationResult
}
