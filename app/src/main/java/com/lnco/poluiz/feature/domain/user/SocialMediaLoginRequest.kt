package com.lnco.poluiz.feature.domain.user

import android.content.Intent
import com.lnco.poluiz.feature.domain.ui.SocialMediaNames

data class SocialMediaLoginRequest(
    val name: SocialMediaNames,
    val intent: Intent? = null,
    val token: String = String(),
)