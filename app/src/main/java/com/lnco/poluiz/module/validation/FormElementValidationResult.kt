package com.lnco.poluiz.module.validation

sealed class FormElementValidationResult<out T> {
    data class Success<out T>(val value: T) : FormElementValidationResult<T>()
    data class Error<out T>(val message: String) : FormElementValidationResult<T>()
}