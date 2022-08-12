package com.lnco.poluiz.feature.presentation.state.element.organism

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.focus.FocusManager
import com.lnco.poluiz.core.validation.UserDataValidation
import com.lnco.poluiz.feature.domain.user.EmailPasswordRegisterRequest
import com.lnco.poluiz.feature.presentation.state.element.molecule.button.LoginRegisterButtonState
import com.lnco.poluiz.feature.presentation.state.element.molecule.text_field.PasswordTextFieldState
import com.lnco.poluiz.feature.presentation.state.element.molecule.text_field.TextFieldState
import com.lnco.poluiz.module.validation.FormElementValidationResult
import com.lnco.poluiz.module.validation.FormValidationResult

class RegisterFormOrganismState(
    focusManager: FocusManager,
    onButtonClick: (EmailPasswordRegisterRequest) -> Unit
) {
    private val userDataValidation = UserDataValidation()

    private val onButtonClick = {
        val result = validate()
        if (result is FormValidationResult.Success)
            onButtonClick(result.validatedData)
    }

    val nameTextFieldState by mutableStateOf(TextFieldState("Name", focusManager))
    val emailTextFieldState by mutableStateOf(TextFieldState("Email", focusManager))
    val passwordTextFieldState by mutableStateOf(PasswordTextFieldState("Password", focusManager))

    var loginRegisterButtonState by mutableStateOf(
        LoginRegisterButtonState(
            "Register",
            this.onButtonClick
        )
    )
        private set

    private fun validate(): FormValidationResult<EmailPasswordRegisterRequest> {
        val nameValidation = userDataValidation.validateName(nameTextFieldState.text)
        val emailValidation = userDataValidation.validateEmail(emailTextFieldState.text)
        val passwordValidation = userDataValidation.validatePassword(passwordTextFieldState.text)

        if (nameValidation is FormElementValidationResult.Error)
            nameTextFieldState.onError(nameValidation.message)

        if (emailValidation is FormElementValidationResult.Error)
            emailTextFieldState.onError(emailValidation.message)

        if (passwordValidation is FormElementValidationResult.Error)
            passwordTextFieldState.onError(passwordValidation.message)

        return when {
            emailTextFieldState.isError || passwordTextFieldState.isError || nameTextFieldState.isError -> FormValidationResult.Error()
            else -> FormValidationResult.Success(
                EmailPasswordRegisterRequest(
                    nameTextFieldState.text,
                    emailTextFieldState.text,
                    passwordTextFieldState.text,
                )
            )
        }
    }
}