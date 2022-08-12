package com.lnco.poluiz.feature.domain.user

data class EmailPasswordLoginRequest(
    val email: String,
    val password: String,
)