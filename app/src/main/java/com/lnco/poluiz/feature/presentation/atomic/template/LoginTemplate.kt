package com.lnco.poluiz.feature.presentation.atomic.template

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import com.lnco.poluiz.feature.presentation.atomic.molecule.TextCenterDivider
import com.lnco.poluiz.feature.presentation.atomic.molecule.TextLinkNavigation
import com.lnco.poluiz.feature.presentation.atomic.organism.SocialMediaButtoOrganism
import com.lnco.poluiz.feature.presentation.atomic.organism.user.LoginFormOrganism
import com.lnco.poluiz.feature.presentation.state.holder.LoginPageStateHolder
import com.lnco.poluiz.feature.presentation.state.holder.rememberLoginPageStateHolder
import com.lnco.poluiz.feature.presentation.theme.Id
import com.lnco.poluiz.feature.presentation.theme.OleoScriptSwashCapsFontFamily
import com.lnco.poluiz.feature.presentation.theme.PoluizTheme
import com.lnco.poluiz.feature.presentation.theme.dimens

@Composable
fun LoginTemplate(
    pageStateHolder: LoginPageStateHolder,
) {
    val constraints = generateConstraints()
    PoluizTheme {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            scaffoldState = pageStateHolder.scaffoldState
        ) {
            ConstraintLayout(
                constraints,
                Modifier.fillMaxSize()
                    .padding(
                        end = MaterialTheme.dimens.normal100,
                        start = MaterialTheme.dimens.normal100,
                        bottom = MaterialTheme.dimens.normal125
                    ),
            ) {

                Text(
                    text = "Poluiz",
                    textAlign = TextAlign.Center,
                    modifier = Modifier.layoutId(Id.TextLogo)
                        .fillMaxWidth()
                        .padding(bottom = MaterialTheme.dimens.normal100),
                    style = MaterialTheme.typography.h1,
                    color = MaterialTheme.colors.primaryVariant,
                    fontFamily = OleoScriptSwashCapsFontFamily
                )

                LoginFormOrganism(
                    Id.FormOrganism,
                    pageStateHolder.loginFormOrganismState
                )

                SocialMediaButtoOrganism(
                    Id.SocialMediaButtonOrganism,
                    pageStateHolder.socialMediaButtonState
                )

                TextCenterDivider(Id.TextCenterDivider, "OR")

                TextLinkNavigation(
                    Id.NavigationText,
                    pageStateHolder.textLinkNavigationState
                )
            }
        }
    }
}

private fun generateConstraints() =
    ConstraintSet {
        val textLogo = createRefFor(Id.TextLogo)
        val loginForm = createRefFor(Id.FormOrganism)
        val registerSpan = createRefFor(Id.NavigationText)
        val textCenterDivider = createRefFor(Id.TextCenterDivider)
        val socialMediaButton = createRefFor(Id.SocialMediaButtonOrganism)

        constrain(textLogo) {
            bottom.linkTo(loginForm.top)
        }

        constrain(loginForm) {
            top.linkTo(parent.top)
            bottom.linkTo(parent.bottom)
        }

        constrain(textCenterDivider) {
            top.linkTo(loginForm.bottom)
            bottom.linkTo(socialMediaButton.top)
        }

        constrain(socialMediaButton) {
            top.linkTo(textCenterDivider.bottom)
            bottom.linkTo(registerSpan.top)
        }

        constrain(registerSpan) {
            bottom.linkTo(parent.bottom)
        }
    }

@Preview
@Composable
private fun Preview() =
    LoginTemplate(
        rememberLoginPageStateHolder(
            onTextNavigationClick = {},
            onEmailPasswordLoginClick = {},
            onSocialMediaButtonClick = {})
    )