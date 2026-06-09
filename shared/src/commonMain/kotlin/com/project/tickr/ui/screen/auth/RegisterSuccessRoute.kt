package com.project.tickr.ui.screen.auth

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.project.tickr.domain.usecase.auth.ObserveSessionUseCase
import com.project.tickr.presentation.navigation.Destination
import com.project.tickr.presentation.navigation.Navigator
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import org.koin.compose.koinInject

@Composable
fun RegisterSuccessRoute(
    navigator: Navigator,
) {
    val observeSession: ObserveSessionUseCase = koinInject()

    LaunchedEffect(Unit) {
        delay(1800L)
        val hasSession = observeSession().first() != null
        if (hasSession) {
            // Email confirmation disabled — langsung ke Home
            navigator.navigate(Destination.Home, popUpToInclusive = true)
        } else {
            // Email confirmation enabled — arahkan ke Login agar user bisa masuk
            // setelah mengklik link konfirmasi di email
            navigator.navigate(Destination.Login, popUpToInclusive = true)
        }
    }

    RegisterSuccessScreen()
}
