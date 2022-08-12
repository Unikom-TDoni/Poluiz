package com.lnco.poluiz.feature.presentation.atomic.template

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import com.lnco.poluiz.feature.domain.quiz.Quiz
import com.lnco.poluiz.feature.presentation.atomic.molecule.button.CheckButton
import com.lnco.poluiz.feature.presentation.atomic.organism.AnswerOrganism
import com.lnco.poluiz.feature.presentation.atomic.organism.QuestionOrganism
import com.lnco.poluiz.feature.presentation.atomic.organism.QuizTopAppBarOrganism
import com.lnco.poluiz.feature.presentation.atomic.organism.quiz.CheckAnswerResultOrganism
import com.lnco.poluiz.feature.presentation.state.holder.QuizPageStateHolder
import com.lnco.poluiz.feature.presentation.state.holder.rememberQuizPageStateHolder
import com.lnco.poluiz.feature.presentation.theme.Id
import com.lnco.poluiz.feature.presentation.theme.PoluizTheme
import com.lnco.poluiz.feature.presentation.theme.dimens

@Composable
fun QuizTemplate(
    pageStateHolder: QuizPageStateHolder
) {
    val constraints = generateConstraints()
    PoluizTheme {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            scaffoldState = pageStateHolder.scaffoldState,
            topBar = { QuizTopAppBarOrganism(pageStateHolder.quizTopAppBarOrganismState) }
        ) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
                ConstraintLayout(
                    constraints,
                    Modifier.fillMaxSize()
                        .padding(
                            top = MaterialTheme.dimens.normal150,
                            end = MaterialTheme.dimens.normal100,
                            start = MaterialTheme.dimens.normal100,
                            bottom = MaterialTheme.dimens.normal100
                        ),
                ) {
                    QuestionOrganism(Id.QuestionOrganism, pageStateHolder.questionOrganismState)
                    AnswerOrganism(Id.AnswerOrganism, pageStateHolder.answerOrganismState)
                }
                CheckAnswerResultOrganism(pageStateHolder.checkAnswerResultOrganismState)
                CheckButton(pageStateHolder.checkButtonOrganismState)
            }
        }
    }
}

private fun generateConstraints() =
    ConstraintSet {
        val questionOrganism = createRefFor(Id.QuestionOrganism)
        val answerButtonOrganism = createRefFor(Id.AnswerOrganism)

        constrain(questionOrganism) {
            top.linkTo(parent.top)
        }

        constrain(answerButtonOrganism) {
            top.linkTo(questionOrganism.bottom)
            bottom.linkTo(parent.bottom)
        }
    }

@Preview
@Composable
private fun Preview() {
    val pageStateHolder = rememberQuizPageStateHolder(
        onBackNavigationClick = {},
        onLifeRunsOut = {}
    )
    pageStateHolder.init(
        listOf(
            Quiz(
                "",
                "Sebutkan Kata Yang",
                "HelloWorld",
                arrayListOf(
                    "Budi",
                    "haha",
                    "c",
                    "k"
                )
            )
        )
    )
    QuizTemplate(pageStateHolder)
}
