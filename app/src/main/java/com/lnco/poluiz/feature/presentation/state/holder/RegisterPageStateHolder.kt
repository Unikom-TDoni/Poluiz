package com.lnco.poluiz.feature.presentation.state.holder

import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.platform.LocalFocusManager
import com.lnco.poluiz.core.navigation.PageNames
import com.lnco.poluiz.feature.domain.user.EmailPasswordRegisterRequest
import com.lnco.poluiz.feature.presentation.state.element.molecule.TextLinkNavigationState
import com.lnco.poluiz.feature.presentation.state.element.organism.RegisterFormOrganismState
import com.lnco.poluiz.module.compose.PageStateHolder
import kotlinx.coroutines.CoroutineScope

@Stable
class RegisterPageStateHolder(
    scaffoldState: ScaffoldState,
    coroutineScope: CoroutineScope,
    focusManager: FocusManager,
    onTextNavigationClick: (String) -> Unit,
    onEmailPasswordRegisterClick: (EmailPasswordRegisterRequest) -> Unit,
) : PageStateHolder(scaffoldState, coroutineScope) {
    val registerFormOrganismState by mutableStateOf(
        RegisterFormOrganismState(
            focusManager,
            onEmailPasswordRegisterClick
        )
    )

    val textLinkNavigationState = TextLinkNavigationState(
        text = "Already have a Poluiz account? Login",
        linkedText = "Login",
        pageNames = PageNames.Login,
        onClick = onTextNavigationClick
    )

}


@Composable
fun rememberRegisterPageStateHolder(
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    focusManager: FocusManager = LocalFocusManager.current,
    onTextNavigationClick: (String) -> Unit,
    onEmailPasswordRegisterClick: (EmailPasswordRegisterRequest) -> Unit,
) = remember(scaffoldState, coroutineScope, focusManager) {
    RegisterPageStateHolder(
        scaffoldState,
        coroutineScope,
        focusManager,
        onTextNavigationClick,
        onEmailPasswordRegisterClick
    )
}