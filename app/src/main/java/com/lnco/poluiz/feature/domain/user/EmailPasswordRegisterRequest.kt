package com.lnco.poluiz.feature.domain.user

data class EmailPasswordRegisterRequest(
    val name: String,
    val email: String,
    val password: String
)