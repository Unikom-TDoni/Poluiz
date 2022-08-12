package com.lnco.poluiz.feature.domain.quiz

import androidx.compose.runtime.Stable

@Stable
class Quiz(
    var answer: String = String(),
    var command: String = String(),
    var question: String = String(),
    var choices: ArrayList<String> = arrayListOf()
)

