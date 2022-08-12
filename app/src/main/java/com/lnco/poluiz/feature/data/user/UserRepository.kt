package com.lnco.poluiz.feature.data.user

import android.content.Intent
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.BeginSignInResult
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.firebase.auth.*
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.lnco.poluiz.feature.domain.ui.SocialMediaNames
import com.lnco.poluiz.feature.domain.user.EmailPasswordLoginRequest
import com.lnco.poluiz.feature.domain.user.EmailPasswordRegisterRequest
import com.lnco.poluiz.feature.domain.user.SocialMediaLoginRequest
import com.lnco.poluiz.module.api.ApiResult
import com.lnco.poluiz.module.api.ErrorResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val signInClient: SignInClient,
) {
    fun getCurrentUser() = firebaseAuth.currentUser

    suspend fun registerWithEmailPassword(request: EmailPasswordRegisterRequest) =
        flow<ApiResult<FirebaseUser, ErrorResponse>> {
            emit(ApiResult.Loading())
            val response =
                firebaseAuth.createUserWithEmailAndPassword(request.email, request.password).await()
            response.user?.let {
                val changeRequest = userProfileChangeRequest { displayName = request.name }
                it.updateProfile(changeRequest).await()
                emit(ApiResult.Success(it))
            } ?: emit(ApiResult.Error(ErrorResponse()))
        }.flowOn(Dispatchers.IO)

    suspend fun loginWithEmailPassword(request: EmailPasswordLoginRequest) =
        flow<ApiResult<FirebaseUser, ErrorResponse>> {
            emit(ApiResult.Loading())
            val response =
                firebaseAuth.signInWithEmailAndPassword(request.email, request.password).await()
            response.user?.let {
                emit(ApiResult.Success(it))
            } ?: emit(ApiResult.Error(ErrorResponse()))
        }.flowOn(Dispatchers.IO)

    suspend fun loginWithSocialMedia(request: SocialMediaLoginRequest) =
        flow<ApiResult<FirebaseUser, ErrorResponse>> {
            emit(ApiResult.Loading())
            val token = request.token.ifEmpty { getGoogleIdToken(request.intent) }
            token?.let {
                val response = firebaseAuth.signInWithCredential(
                    getFirebaseAuthCredential(
                        request.name,
                        token
                    )
                ).await()
                response.user?.let {
                    emit(ApiResult.Success(it))
                } ?: emit(ApiResult.Error(ErrorResponse()))
            } ?: emit(ApiResult.Error(ErrorResponse()))
        }.flowOn(Dispatchers.IO)

    suspend fun loginWithGoogleClient() =
        flow<ApiResult<BeginSignInResult, ErrorResponse>> {
            emit(ApiResult.Loading())
            val result = signInClient.beginSignIn(getGoogleSignInRequest()).await()
            emit(ApiResult.Success(result))
        }.flowOn(Dispatchers.IO)

    suspend fun logout() =
        flow<ApiResult<Boolean, ErrorResponse>> {
            emit(ApiResult.Loading())
            firebaseAuth.signOut()
            signInClient.signOut().await()
            emit(ApiResult.Success(true))
        }.flowOn(Dispatchers.IO)

    private fun getGoogleIdToken(intent: Intent?) =
        signInClient.getSignInCredentialFromIntent(intent).googleIdToken

    private fun getGoogleSignInRequest() =
        BeginSignInRequest.builder()
            .setGoogleIdTokenRequestOptions(
                BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                    .setSupported(true)
                    .setServerClientId("409173111577-gdmo7fjqh0ai57m7mmrclokoll4fdmc1.apps.googleusercontent.com")
                    .setFilterByAuthorizedAccounts(false)
                    .build()
            )
            .build()

    private fun getFirebaseAuthCredential(name: SocialMediaNames, token: String): AuthCredential =
        when (name) {
            SocialMediaNames.Google -> GoogleAuthProvider.getCredential(token, null)
            else -> FacebookAuthProvider.getCredential(token)
        }
}