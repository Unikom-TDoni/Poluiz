package com.lnco.poluiz.feature.data.quiz

import com.google.firebase.firestore.FirebaseFirestore
import com.lnco.poluiz.feature.domain.quiz.Quiz
import com.lnco.poluiz.module.api.ApiResult
import com.lnco.poluiz.module.api.ErrorResponse
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class QuizRepository @Inject constructor(
    private val firestore: FirebaseFirestore
) {
    suspend fun fetch() =
        flow<ApiResult<Collection<Quiz>, ErrorResponse>> {
            emit(ApiResult.Loading())
            val result = firestore.collection("Quiz").get().await()
            if (result.isEmpty) emit(ApiResult.Error(ErrorResponse(message = "Data Empty")))
            else emit(ApiResult.Success(result.toObjects(Quiz::class.java).shuffled()))
        }
}