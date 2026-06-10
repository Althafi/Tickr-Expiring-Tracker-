package com.project.tickr.ui.common

import androidx.compose.runtime.Composable

@Composable
expect fun PlatformDatePicker(
    show: Boolean,
    initialDateMillis: Long?,
    onDateSelected: (Long) -> Unit,
    onDismiss: () -> Unit,
)
