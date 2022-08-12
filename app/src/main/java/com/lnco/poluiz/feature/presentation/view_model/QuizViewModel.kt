package com.lnco.poluiz.feature.presentation.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lnco.poluiz.feature.data.quiz.QuizRepository
import com.lnco.poluiz.feature.domain.quiz.Quiz
import com.lnco.poluiz.module.api.ApiResult
import com.lnco.poluiz.module.api.ErrorResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuizViewModel @Inject constructor(
    private val quizRepository: QuizRepository
) : ViewModel() {

    private val _quizFlow = Channel<ApiResult<Collection<Quiz>, ErrorResponse>>()
    val quizFlow = _quizFlow.receiveAsFlow()

    fun fetch() =
        viewModelScope.launch {
            quizRepository.fetch()
                .catch {
                    _quizFlow.send(ApiResult.Error(ErrorResponse(throwable = it)))
                }
                .collect {
                    _quizFlow.send(it)
                }
        }
}