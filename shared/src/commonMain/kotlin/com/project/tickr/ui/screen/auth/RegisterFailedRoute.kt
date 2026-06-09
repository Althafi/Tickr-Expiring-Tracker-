package com.project.tickr.ui.screen.auth

import androidx.compose.runtime.Composable
import com.project.tickr.presentation.common.AuthErrorStore
import com.project.tickr.presentation.navigation.Destination
import com.project.tickr.presentation.navigation.Navigator
import org.koin.compose.koinInject

@Composable
fun RegisterFailedRoute(
    navigator: Navigator,
) {
    val authErrorStore: AuthErrorStore = koinInject()

    RegisterFailedScreen(
        errorMessage = authErrorStore.lastRegisterError,
        onRetry = { navigator.back() },
        onBackToLogin = { navigator.navigate(Destination.Login, popUpToInclusive = true) },
    )
}
