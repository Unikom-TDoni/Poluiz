package com.lnco.poluiz.feature.presentation.state.holder

import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import com.lnco.poluiz.feature.domain.quiz.Quiz
import com.lnco.poluiz.feature.domain.ui.QuestionType
import com.lnco.poluiz.feature.presentation.state.element.molecule.button.CheckButtonState
import com.lnco.poluiz.feature.presentation.state.element.organism.AnswerOrganismState
import com.lnco.poluiz.feature.presentation.state.element.organism.CheckAnswerResultOrganismState
import com.lnco.poluiz.feature.presentation.state.element.organism.QuestionOrganismState
import com.lnco.poluiz.feature.presentation.state.element.organism.QuizTopAppBarOrganismState
import com.lnco.poluiz.module.compose.PageStateHolder
import kotlinx.coroutines.CoroutineScope

@Stable
class QuizPageStateHolder(
    scaffoldState: ScaffoldState,
    coroutineScope: CoroutineScope,
    onBackNavigation: () -> Unit,
    private val onLifeRunsOut: (Int) -> Unit,
) : PageStateHolder(scaffoldState, coroutineScope) {

    private var currentScore = 0
    private var currentPlayerLife = 3
    private var currentQuizIndex = 0
    private var currentQuiz = Quiz()
    private var userAnswer = String()
    private var quizCollection: Collection<Quiz> = listOf()

    val quizTopAppBarOrganismState = QuizTopAppBarOrganismState(3, onBackNavigation)
    val questionOrganismState =
        QuestionOrganismState(String(), String(), String(), QuestionType.Text)

    val answerOrganismState = AnswerOrganismState(currentQuiz.choices) {
        userAnswer = it
        checkButtonOrganismState.enableButton()
    }

    val checkAnswerResultOrganismState = CheckAnswerResultOrganismState()

    val checkButtonOrganismState = CheckButtonState("Check Answer") {
        if (it) {
            if (currentPlayerLife == 0) onLifeRunsOut(currentScore)
            else showQuiz()
        } else checkingUserAnswer()
    }

    fun init(quiz: Collection<Quiz>) {
        quizCollection = quiz
        showQuiz()
    }

    private fun checkingUserAnswer() {
        val isAnswerCorrect = userAnswer == currentQuiz.answer
        checkButtonOrganismState.changeStyle(isAnswerCorrect)
        checkAnswerResultOrganismState.showResult(currentQuiz.answer, isAnswerCorrect)

        if (isAnswerCorrect) {
            currentScore += 10
            return
        }
        currentPlayerLife--
        quizTopAppBarOrganismState.updateLife(currentPlayerLife)
    }

    private fun showQuiz() {
        if (currentQuizIndex == quizCollection.size - 1) {
            quizCollection.shuffled()
            currentQuizIndex = 0
        } else currentQuizIndex++

        currentQuiz = quizCollection.elementAt(currentQuizIndex)

        checkButtonOrganismState.resetState()
        checkAnswerResultOrganismState.hideResult()
        answerOrganismState.change(currentQuiz.choices)
        questionOrganismState.change(currentQuiz.command, currentQuiz.question, "")
    }
}

@Composable
fun rememberQuizPageStateHolder(
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    onBackNavigationClick: () -> Unit,
    onLifeRunsOut: (Int) -> Unit,
) = remember(scaffoldState, coroutineScope) {
    QuizPageStateHolder(scaffoldState, coroutineScope, onBackNavigationClick, onLifeRunsOut)
}