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
import com.lnco.poluiz.feature.presentation.atomic.molecule.TextLinkNavigation
import com.lnco.poluiz.feature.presentation.atomic.organism.user.RegisterFormOrganism
import com.lnco.poluiz.feature.presentation.state.holder.RegisterPageStateHolder
import com.lnco.poluiz.feature.presentation.state.holder.rememberRegisterPageStateHolder
import com.lnco.poluiz.feature.presentation.theme.Id
import com.lnco.poluiz.feature.presentation.theme.OleoScriptSwashCapsFontFamily
import com.lnco.poluiz.feature.presentation.theme.PoluizTheme
import com.lnco.poluiz.feature.presentation.theme.dimens

@Composable
fun RegisterTemplate(
    pageStateHolder: RegisterPageStateHolder
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

                RegisterFormOrganism(
                    Id.FormOrganism,
                    pageStateHolder.registerFormOrganismState
                )

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
        val loginSpan = createRefFor(Id.NavigationText)
        val registerForm = createRefFor(Id.FormOrganism)

        constrain(textLogo) {
            bottom.linkTo(registerForm.top)
        }

        constrain(registerForm) {
            top.linkTo(parent.top)
            bottom.linkTo(parent.bottom)
        }

        constrain(loginSpan) {
            bottom.linkTo(parent.bottom)
        }
    }

@Preview
@Composable
private fun Preview() =
    RegisterTemplate(
        rememberRegisterPageStateHolder(
            onTextNavigationClick = {},
            onEmailPasswordRegisterClick = {})
    )