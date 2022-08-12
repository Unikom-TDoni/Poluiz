package com.lnco.poluiz.feature.presentation.page

import android.app.Activity.RESULT_OK
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts.StartIntentSenderForResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.platform.LocalContext
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.lnco.poluiz.core.LocalFacebookCallbackManager
import com.lnco.poluiz.core.PoluizAppState
import com.lnco.poluiz.core.navigation.PageNames
import com.lnco.poluiz.feature.domain.ui.SocialMediaNames
import com.lnco.poluiz.feature.domain.user.SocialMediaLoginRequest
import com.lnco.poluiz.feature.presentation.atomic.organism.LoadingDialog
import com.lnco.poluiz.feature.presentation.atomic.template.LoginTemplate
import com.lnco.poluiz.feature.presentation.state.holder.rememberLoginPageStateHolder
import com.lnco.poluiz.feature.presentation.view_model.UserViewModel
import com.lnco.poluiz.module.api.ApiResult
import com.lnco.poluiz.module.compose.collectWithLifecycle
import com.lnco.poluiz.module.compose.findActivity

@Composable
fun LoginPage(
    appState: PoluizAppState,
    userViewModel: UserViewModel,
) {
    val context = LocalContext.current
    val pageStateHolder = rememberLoginPageStateHolder(
        onTextNavigationClick = {
            appState.navigateTo(PageNames.valueOf(it))
        },
        onEmailPasswordLoginClick = {
            userViewModel.loginWithEmailPassword(it)
        }, onSocialMediaButtonClick = {
            when (it) {
                SocialMediaNames.Google -> userViewModel.loginGoogleClient()
                else -> LoginManager.getInstance()
                    .logInWithReadPermissions(context.findActivity(), listOf("public_profile"))
            }
        })

    val googleLoginLauncher = rememberLauncherForActivityResult(StartIntentSenderForResult()) {
        when (it.resultCode) {
            RESULT_OK -> userViewModel.loginWithSocialMedia(
                SocialMediaLoginRequest(
                    SocialMediaNames.Google,
                    it.data
                )
            )
            else -> pageStateHolder.onErrorCallback("Login Canceled")
        }
    }

    val callbackManager = LocalFacebookCallbackManager.current
    DisposableEffect(Unit) {
        LoginManager.getInstance().registerCallback(
            callbackManager,
            object : FacebookCallback<LoginResult> {
                override fun onSuccess(result: LoginResult) {
                    userViewModel.loginWithSocialMedia(
                        SocialMediaLoginRequest(
                            SocialMediaNames.Facebook,
                            token = result.accessToken.token
                        )
                    )
                }

                override fun onCancel() {
                    pageStateHolder.onErrorCallback("Login Canceled")
                }

                override fun onError(error: FacebookException) {
                    pageStateHolder.onErrorCallback(error.message.toString())
                }
            }
        )
        onDispose {
            LoginManager.getInstance().unregisterCallback(callbackManager)
        }
    }

    userViewModel.userFlow.collectWithLifecycle {
        when (it) {
            is ApiResult.Success -> {
                pageStateHolder.dismissLoading()
                appState.navigateTo(PageNames.Login)
            }
            is ApiResult.Loading -> pageStateHolder.onLoadingCallback()
            is ApiResult.Error -> pageStateHolder.onErrorCallback(it.response.throwable.toString())
        }
    }

    userViewModel.googleClientFlow.collectWithLifecycle {
        when (it) {
            is ApiResult.Success -> {
                googleLoginLauncher.launch(
                    IntentSenderRequest.Builder(it.response.pendingIntent.intentSender).build()
                )
            }
            is ApiResult.Loading -> pageStateHolder.onLoadingCallback(false)
            is ApiResult.Error -> pageStateHolder.onErrorCallback(it.response.throwable.toString())
        }
    }

    LoginTemplate(pageStateHolder)
    LoadingDialog(pageStateHolder)
}