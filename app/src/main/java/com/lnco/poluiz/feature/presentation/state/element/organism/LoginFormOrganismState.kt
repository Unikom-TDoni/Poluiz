package com.lnco.poluiz.feature.presentation.state.element.organism

import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.focus.FocusManager
import com.lnco.poluiz.core.validation.UserDataValidation
import com.lnco.poluiz.feature.domain.user.EmailPasswordLoginRequest
import com.lnco.poluiz.feature.presentation.state.element.molecule.button.LoginRegisterButtonState
import com.lnco.poluiz.feature.presentation.state.element.molecule.text_field.PasswordTextFieldState
import com.lnco.poluiz.feature.presentation.state.element.molecule.text_field.TextFieldState
import com.lnco.poluiz.module.validation.FormElementValidationResult
import com.lnco.poluiz.module.validation.FormValidationResult

@Stable
class LoginFormOrganismState(
    focusManager: FocusManager,
    onButtonClick: (EmailPasswordLoginRequest) -> Unit
) {
    private val userDataValidation = UserDataValidation()

    private val onButtonClick = {
        val result = validate()
        if (result is FormValidationResult.Success)
            onButtonClick(result.validatedData)
    }

    val emailTextFieldState by mutableStateOf(TextFieldState("Email", focusManager))
    val passwordTextFieldState by mutableStateOf(PasswordTextFieldState("Password", focusManager))
    var loginRegisterButtonState by mutableStateOf(
        LoginRegisterButtonState(
            "Login",
            this.onButtonClick
        )
    )
        private set

    private fun validate(): FormValidationResult<EmailPasswordLoginRequest> {
        val emailValidation = userDataValidation.validateEmail(emailTextFieldState.text)
        val passwordValidation = userDataValidation.validatePassword(passwordTextFieldState.text)

        if (emailValidation is FormElementValidationResult.Error)
            emailTextFieldState.onError(emailValidation.message)

        if (passwordValidation is FormElementValidationResult.Error)
            passwordTextFieldState.onError(passwordValidation.message)

        return when {
            emailTextFieldState.isError || passwordTextFieldState.isError -> FormValidationResult.Error()
            else -> FormValidationResult.Success(
                EmailPasswordLoginRequest(
                    emailTextFieldState.text,
                    passwordTextFieldState.text
                )
            )
        }
    }
}