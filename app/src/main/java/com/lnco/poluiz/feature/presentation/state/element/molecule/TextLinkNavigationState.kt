package com.lnco.poluiz.feature.presentation.state.element.molecule

import com.lnco.poluiz.core.navigation.PageNames

class TextLinkNavigationState(
    val text: String,
    val linkedText: String,
    val pageNames: PageNames,
    val onClick: (String) -> Unit,
)