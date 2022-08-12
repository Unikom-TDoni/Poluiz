package com.lnco.poluiz.feature.data.leaderboard

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.lnco.poluiz.feature.domain.leaderboard.Leaderboard
import com.lnco.poluiz.feature.domain.leaderboard.UpdateScoreRequest
import com.lnco.poluiz.module.api.ApiResult
import com.lnco.poluiz.module.api.ErrorResponse
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class LeaderboardRepository @Inject constructor(
    private val firestore: FirebaseFirestore
) {
    fun fetch() =
        flow<ApiResult<Collection<Leaderboard>, ErrorResponse>> {
            emit(ApiResult.Loading())
            val response = firestore.collection("Leaderboard")
                .orderBy("score", Query.Direction.DESCENDING)
                .limit(30).get().await()
            if (response.isEmpty) emit(ApiResult.Error(ErrorResponse(message = "Data Empty")))
            else emit(ApiResult.Success(response.toObjects(Leaderboard::class.java)))
        }

    fun update(uid: String, request: UpdateScoreRequest) =
        flow<ApiResult<Int, ErrorResponse>> {
            emit(ApiResult.Loading())
            firestore.collection("Leaderboard")
                .document(uid)
                .set(request).await()
            emit(ApiResult.Success(request.score))
        }

    fun fetchUserHighScore(uid: String) =
        flow<ApiResult<Long, ErrorResponse>> {
            emit(ApiResult.Loading())
            val response = firestore.collection("Leaderboard").document(uid).get().await()
            if (response.exists()) emit(ApiResult.Success(response.getLong("score")!!))
            else emit(ApiResult.Error(ErrorResponse(message = "Data Empty")))
        }

}