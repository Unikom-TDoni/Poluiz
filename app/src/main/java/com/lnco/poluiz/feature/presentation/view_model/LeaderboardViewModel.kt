package com.lnco.poluiz.feature.presentation.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lnco.poluiz.feature.data.leaderboard.LeaderboardRepository
import com.lnco.poluiz.feature.domain.leaderboard.Leaderboard
import com.lnco.poluiz.feature.domain.leaderboard.UpdateScoreRequest
import com.lnco.poluiz.module.api.ApiResult
import com.lnco.poluiz.module.api.ErrorResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LeaderboardViewModel @Inject constructor(
    private val repository: LeaderboardRepository
) : ViewModel() {

    var currentUserHighScore = 0L
        private set

    private var _leaderboardFlow = Channel<ApiResult<Collection<Leaderboard>, ErrorResponse>>()
    val leaderboardFlow = _leaderboardFlow.receiveAsFlow()

    private var _updateFlow = Channel<ApiResult<Int, ErrorResponse>>()
    val updateFlow = _updateFlow.receiveAsFlow()

    fun fetchUserHighScore(uid: String) =
        viewModelScope.launch {
            repository.fetchUserHighScore(uid)
                .catch {
                    currentUserHighScore = 0
                }.collect {
                    currentUserHighScore = when (it) {
                        is ApiResult.Success -> it.response
                        else -> 0
                    }
                }
        }

    fun fetch() =
        viewModelScope.launch {
            repository.fetch()
                .catch {
                    _leaderboardFlow.send(ApiResult.Error(ErrorResponse(throwable = it)))
                }.collect {
                    _leaderboardFlow.send(it)
                }
        }

    fun update(uid: String, request: UpdateScoreRequest) =
        viewModelScope.launch {
            repository.update(uid, request)
                .catch {
                    _updateFlow.send(ApiResult.Error(ErrorResponse(throwable = it)))
                }.collect {
                    _updateFlow.send(it)
                }
        }
}