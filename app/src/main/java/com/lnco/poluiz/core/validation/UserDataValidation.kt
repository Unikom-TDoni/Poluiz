package com.lnco.poluiz.core.validation

import android.util.Patterns
import com.lnco.poluiz.module.validation.FormElementValidationResult
import java.util.*

class UserDataValidation {

    fun validateEmail(email: String) = when {
        email.isBlank() -> FormElementValidationResult.Error("The email field is required")
        !Patterns.EMAIL_ADDRESS.matcher(email)
            .matches() -> FormElementValidationResult.Error("Email address must have a valid format")
        else -> FormElementValidationResult.Success(email)
    }

    fun validatePassword(password: String) = when {
        password.isBlank() -> FormElementValidationResult.Error("The password field is required")
        password.length < 8 -> FormElementValidationResult.Error("Password length must be at least 8 character")
        else -> FormElementValidationResult.Success(password)
    }

    fun validateName(name: String) = when {
        name.isBlank() -> FormElementValidationResult.Error("The name field is required")
        name.length < 3 -> FormElementValidationResult.Error("Name length must be at least 3 character")
        else -> FormElementValidationResult.Success(name)
    }
}
