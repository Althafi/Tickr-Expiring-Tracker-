package com.project.tickr.ui.screen.auth

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.project.tickr.ui.theme.TickrTheme
import org.jetbrains.compose.resources.painterResource
import tickrapp.shared.generated.resources.Res
import tickrapp.shared.generated.resources.ic_register_failed

@Composable
fun RegisterFailedScreen(
    errorMessage: String,
    onRetry: () -> Unit,
    onBackToLogin: () -> Unit,
) {
    Scaffold(
        containerColor = TickrTheme.colors.background,
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            FailedAnimation()

            Spacer(modifier = Modifier.height(TickrTheme.spacing.xl))

            Text(
                text = "Registrasi Gagal",
                style = TickrTheme.typography.onboardingTitle,
                color = TickrTheme.colors.critical,
                textAlign = TextAlign.Center,
            )

            Spacer(modifier = Modifier.height(TickrTheme.spacing.md))

            Text(
                text = errorMessage,
                style = TickrTheme.typography.body,
                color = TickrTheme.colors.textSecondary,
                textAlign = TextAlign.Center,
            )

            Spacer(modifier = Modifier.height(TickrTheme.spacing.xxl))

            PrimaryButton(
                text = "Coba Lagi",
                onClick = onRetry,
            )

            Spacer(modifier = Modifier.height(TickrTheme.spacing.md))

            SecondaryOutlinedButton(
                text = "Kembali ke Login",
                onClick = onBackToLogin,
            )
        }
    }
}

@Composable
private fun FailedAnimation(modifier: Modifier = Modifier) {
    var started by remember { mutableStateOf(false) }

    val scale by animateFloatAsState(
        targetValue = if (started) 1f else 0.5f,
        animationSpec = tween(durationMillis = 500, easing = FastOutSlowInEasing),
        label = "failed_scale",
    )
    val alpha by animateFloatAsState(
        targetValue = if (started) 1f else 0f,
        animationSpec = tween(durationMillis = 350),
        label = "failed_alpha",
    )

    LaunchedEffect(Unit) { started = true }

    Image(
        painter = painterResource(Res.drawable.ic_register_failed),
        contentDescription = null,
        modifier = modifier
            .size(120.dp)
            .graphicsLayer(scaleX = scale, scaleY = scale, alpha = alpha),
        contentScale = ContentScale.Fit,
    )
}

// ─── Preview ─────────────────────────────────────────────────────────────────

@Preview(name = "RegisterFailed · Light", showSystemUi = true)
@Composable
private fun PreviewFailedLight() {
    TickrTheme(darkTheme = false) {
        RegisterFailedScreen(
            errorMessage = "Koneksi gagal. Periksa koneksi internet Anda.",
            onRetry = {},
            onBackToLogin = {},
        )
    }
}

@Preview(name = "RegisterFailed · Dark", showSystemUi = true)
@Composable
private fun PreviewFailedDark() {
    TickrTheme(darkTheme = true) {
        RegisterFailedScreen(
            errorMessage = "Terjadi kesalahan pada server. Coba lagi beberapa saat.",
            onRetry = {},
            onBackToLogin = {},
        )
    }
}
