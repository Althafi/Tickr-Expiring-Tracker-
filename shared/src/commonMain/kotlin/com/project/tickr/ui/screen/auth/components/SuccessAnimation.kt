package com.project.tickr.ui.screen.auth.components

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource
import tickrapp.shared.generated.resources.Res
import tickrapp.shared.generated.resources.ic_register_success

@Composable
fun SuccessAnimation(
    modifier: Modifier = Modifier,
) {
    var started by remember { mutableStateOf(false) }

    val scale by animateFloatAsState(
        targetValue = if (started) 1f else 0.5f,
        animationSpec = tween(durationMillis = 500, easing = FastOutSlowInEasing),
        label = "success_scale",
    )
    val alpha by animateFloatAsState(
        targetValue = if (started) 1f else 0f,
        animationSpec = tween(durationMillis = 350),
        label = "success_alpha",
    )

    LaunchedEffect(Unit) { started = true }

    Image(
        painter = painterResource(Res.drawable.ic_register_success),
        contentDescription = null,
        modifier = modifier
            .size(120.dp)
            .graphicsLayer(scaleX = scale, scaleY = scale, alpha = alpha),
        contentScale = ContentScale.Fit,
    )
}
