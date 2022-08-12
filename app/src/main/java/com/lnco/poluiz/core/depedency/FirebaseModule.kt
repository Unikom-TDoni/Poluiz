package com.lnco.poluiz.core.depedency

import android.content.Context
import com.facebook.login.LoginManager
import com.google.android.gms.auth.api.identity.Identity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object FirebaseModule {
    @Provides
    fun provideFirebaseAuth() =
        FirebaseAuth.getInstance()

    @Provides
    fun provideFirebaseFirestore() =
        FirebaseFirestore.getInstance()

    @Provides
    fun provideSignInClient(@ApplicationContext context: Context) =
        Identity.getSignInClient(context)

    @Provides
    fun provideFacebookLoginManager() =
        LoginManager.getInstance()
}