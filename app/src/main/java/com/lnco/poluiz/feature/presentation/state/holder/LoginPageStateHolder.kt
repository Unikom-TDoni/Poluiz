package com.lnco.poluiz.feature.presentation.state.holder

import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.platform.LocalFocusManager
import com.google.common.collect.ImmutableCollection
import com.google.common.collect.ImmutableList
import com.lnco.poluiz.R
import com.lnco.poluiz.core.navigation.PageNames
import com.lnco.poluiz.feature.domain.ui.SocialMediaNames
import com.lnco.poluiz.feature.domain.user.EmailPasswordLoginRequest
import com.lnco.poluiz.feature.presentation.state.element.molecule.TextLinkNavigationState
import com.lnco.poluiz.feature.presentation.state.element.molecule.button.SocialMediaButtonState
import com.lnco.poluiz.feature.presentation.state.element.organism.LoginFormOrganismState
import com.lnco.poluiz.module.compose.PageStateHolder
import kotlinx.coroutines.CoroutineScope

@Stable
class LoginPageStateHolder(
    scaffoldState: ScaffoldState,
    coroutineScope: CoroutineScope,
    focusManager: FocusManager,
    onTextNavigationClick: (String) -> Unit,
    onSocialMediaButtonClick: (SocialMediaNames) -> Unit,
    onEmailPasswordLoginClick: (EmailPasswordLoginRequest) -> Unit,
) : PageStateHolder(scaffoldState, coroutineScope) {
    val socialMediaButtonState: ImmutableCollection<SocialMediaButtonState> = ImmutableList.of(
        SocialMediaButtonState(
            text = SocialMediaNames.Google.toString(),
            icon = R.drawable.ic_google,
        ) {
            onSocialMediaButtonClick(SocialMediaNames.Google)
        },
        SocialMediaButtonState(
            text = SocialMediaNames.Facebook.toString(),
            icon = R.drawable.ic_facebook,
        ) {
            onSocialMediaButtonClick(SocialMediaNames.Facebook)
        }
    )

    val loginFormOrganismState by mutableStateOf(LoginFormOrganismState(focusManager, onEmailPasswordLoginClick))

    val textLinkNavigationState = TextLinkNavigationState(
        "Don't Have Poluiz Account? Register",
        "Register",
        PageNames.Register,
        onTextNavigationClick
    )

}

@Composable
fun rememberLoginPageStateHolder(
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    focusManager: FocusManager = LocalFocusManager.current,
    onTextNavigationClick: (String) -> Unit,
    onSocialMediaButtonClick: (SocialMediaNames) -> Unit,
    onEmailPasswordLoginClick: (EmailPasswordLoginRequest) -> Unit,
) = remember(scaffoldState, coroutineScope, onSocialMediaButtonClick) {
    LoginPageStateHolder(
        scaffoldState,
        coroutineScope,
        focusManager,
        onTextNavigationClick,
        onSocialMediaButtonClick,
        onEmailPasswordLoginClick
    )
}