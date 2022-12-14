package com.lnco.poluiz.module.api

data class ErrorResponse(
    val code: Int? = null,
    val message: String? = null,
    val throwable: Throwable? = null,
)