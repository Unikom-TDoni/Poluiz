package com.lnco.poluiz.feature.presentation.atomic.organism.user

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.lnco.poluiz.feature.presentation.atomic.molecule.button.LoginRegisterButton
import com.lnco.poluiz.feature.presentation.atomic.molecule.text_field.EmailTextField
import com.lnco.poluiz.feature.presentation.atomic.molecule.text_field.PasswordTextField
import com.lnco.poluiz.feature.presentation.state.element.organism.LoginFormOrganismState
import com.lnco.poluiz.feature.presentation.theme.Id
import com.lnco.poluiz.feature.presentation.theme.PoluizTheme
import com.lnco.poluiz.feature.presentation.theme.dimens

@Composable
fun LoginFormOrganism(
    id: String,
    state: LoginFormOrganismState
) = Column(
    Modifier.layoutId(id)
) {
    Text("Login", style = MaterialTheme.typography.h5, fontWeight = FontWeight.SemiBold)
    Spacer(Modifier.height(MaterialTheme.dimens.mini40))
    EmailTextField(state.emailTextFieldState)
    Spacer(Modifier.height(MaterialTheme.dimens.mini120))
    PasswordTextField(state.passwordTextFieldState)
    Spacer(Modifier.height(MaterialTheme.dimens.normal150))
    LoginRegisterButton(state.loginRegisterButtonState)
}

@Preview
@Composable
private fun Preview() =
    PoluizTheme {
        LoginFormOrganism(
            Id.FormOrganism,
            LoginFormOrganismState(LocalFocusManager.current) {}
        )
    }