package com.lnco.poluiz.feature.presentation.atomic.molecule

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.ClickableText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.constraintlayout.compose.layoutId
import com.lnco.poluiz.feature.presentation.state.element.molecule.TextLinkNavigationState
import com.lnco.poluiz.feature.presentation.theme.moonstone_blue

@Composable
fun TextLinkNavigation(
    id: String,
    state: TextLinkNavigationState
) {
    val annotatedLinkString = buildAnnotatedString {
        val startIndex = state.text.indexOf(state.linkedText)
        val endIndex = startIndex + state.linkedText.length
        append(state.text)
        addStyle(
            style = SpanStyle(
                color = moonstone_blue,
                textDecoration = TextDecoration.Underline
            ), start = startIndex, end = endIndex
        )
        addStringAnnotation(
            tag = "Navigation",
            annotation = state.pageNames.toString(),
            start = startIndex,
            end = endIndex
        )
    }

    ClickableText(
        text = annotatedLinkString,
        style = TextStyle(textAlign = TextAlign.Center),
        modifier = Modifier.layoutId(id).fillMaxWidth(),
        onClick = {
            annotatedLinkString.getStringAnnotations("Navigation", it, it)
                .firstOrNull()?.let { route ->
                    state.onClick(route.item)
                }
        })
}