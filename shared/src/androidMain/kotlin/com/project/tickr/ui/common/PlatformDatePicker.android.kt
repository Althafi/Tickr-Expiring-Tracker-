package com.project.tickr.ui.common

import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember

@OptIn(ExperimentalMaterial3Api::class)
@Composable
actual fun PlatformDatePicker(
    show: Boolean,
    initialDateMillis: Long?,
    onDateSelected: (Long) -> Unit,
    onDismiss: () -> Unit,
) {
    if (!show) return

    val state = rememberDatePickerState(initialSelectedDateMillis = initialDateMillis)
    val confirmEnabled = remember { derivedStateOf { state.selectedDateMillis != null } }

    DatePickerDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(
                onClick = {
                    state.selectedDateMillis?.let { onDateSelected(it) }
                    onDismiss()
                },
                enabled = confirmEnabled.value,
            ) { Text("OK") }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) { Text("Batal") }
        },
    ) {
        DatePicker(state = state)
    }
}
