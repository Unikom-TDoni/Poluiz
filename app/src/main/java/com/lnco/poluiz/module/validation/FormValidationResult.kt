package com.lnco.poluiz.module.validation

sealed class FormValidationResult<out T> {
    class Error<out T> : FormValidationResult<T>()
    data class Success<out T>(val validatedData: T) : FormValidationResult<T>()
}