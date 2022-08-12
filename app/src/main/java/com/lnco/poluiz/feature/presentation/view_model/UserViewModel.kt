package com.lnco.poluiz.feature.presentation.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.identity.BeginSignInResult
import com.google.firebase.auth.FirebaseUser
import com.lnco.poluiz.feature.data.user.UserRepository
import com.lnco.poluiz.feature.domain.user.EmailPasswordLoginRequest
import com.lnco.poluiz.feature.domain.user.EmailPasswordRegisterRequest
import com.lnco.poluiz.feature.domain.user.SocialMediaLoginRequest
import com.lnco.poluiz.module.api.ApiResult
import com.lnco.poluiz.module.api.ErrorResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val userRepository: UserRepository,
) : ViewModel() {

    private val _userFlow = Channel<ApiResult<FirebaseUser, ErrorResponse>>()
    val userFlow = _userFlow.receiveAsFlow()

    private val _googleClientFlow = Channel<ApiResult<BeginSignInResult, ErrorResponse>>()
    val googleClientFlow = _googleClientFlow.receiveAsFlow()

    private val _facebookClientFlow = Channel<ApiResult<LoginResult, ErrorResponse>>()
    val facebookClientFlow = _facebookClientFlow.receiveAsFlow()

    private val _userLogoutFlow = Channel<ApiResult<Boolean, ErrorResponse>>()
    val userLogoutFlow = _userLogoutFlow.receiveAsFlow()

    fun getCurrentUser() = userRepository.getCurrentUser()

    fun registerWithEmailPassword(request: EmailPasswordRegisterRequest) =
        viewModelScope.launch {
            userRepository.registerWithEmailPassword(request)
                .catch {
                    _userFlow.send(ApiResult.Error(ErrorResponse(throwable = it)))
                }.collect {
                    _userFlow.send(it)
                }
        }

    fun loginWithEmailPassword(request: EmailPasswordLoginRequest) =
        viewModelScope.launch {
            userRepository.loginWithEmailPassword(request)
                .catch {
                    _userFlow.send(ApiResult.Error(ErrorResponse(throwable = it)))
                }.collect {
                    _userFlow.send(it)
                }
        }

    fun loginWithSocialMedia(request: SocialMediaLoginRequest) =
        viewModelScope.launch {
            userRepository.loginWithSocialMedia(request)
                .catch {
                    _userFlow.send(ApiResult.Error(ErrorResponse(throwable = it)))
                }.collect {
                    _userFlow.send(it)
                }
        }

    fun loginGoogleClient() =
        viewModelScope.launch {
            userRepository.loginWithGoogleClient()
                .catch {
                    _googleClientFlow.send(ApiResult.Error(ErrorResponse(throwable = it)))
                }.collect {
                    _googleClientFlow.send(it)
                }
        }

    fun logout() =
        viewModelScope.launch {
            userRepository.logout()
                .catch {
                    _userLogoutFlow.send(ApiResult.Error(ErrorResponse(throwable = it)))
                }.collect {
                    _userLogoutFlow.send(it)
                }
        }
}