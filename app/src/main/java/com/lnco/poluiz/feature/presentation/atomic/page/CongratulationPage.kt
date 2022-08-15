package com.lnco.poluiz.feature.presentation.atomic.page

import androidx.compose.runtime.Composable
import com.lnco.poluiz.core.PoluizAppState
import com.lnco.poluiz.core.navigation.PageNames
import com.lnco.poluiz.feature.presentation.atomic.template.CongratulationTemplate
import com.lnco.poluiz.feature.presentation.state.holder.rememberCongratulationPageStateHolder

@Composable
fun CongratulationPage(
    appState: PoluizAppState,
    totalScore: Int,
) {
    val pageStateHolder = rememberCongratulationPageStateHolder(totalScore = totalScore) {
        appState.navigateTo(PageNames.Home)
    }

    CongratulationTemplate(pageStateHolder)
}