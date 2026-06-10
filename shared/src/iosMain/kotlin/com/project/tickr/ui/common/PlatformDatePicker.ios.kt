package com.project.tickr.ui.common

import androidx.compose.runtime.Composable

@Composable
actual fun PlatformDatePicker(
    show: Boolean,
    initialDateMillis: Long?,
    onDateSelected: (Long) -> Unit,
    onDismiss: () -> Unit,
) {
    // TODO(user): implement with UIKitView + UIDatePicker or PHPickerViewController
}
