package com.project.tickr.ui.screen.home.additem

import androidx.compose.runtime.Composable
import com.project.tickr.presentation.additem.AddItemViewModel

@Composable
fun AddItemRoute(
    viewModel: AddItemViewModel,
    onDismiss: () -> Unit,
) {
    AddItemSheet(viewModel = viewModel, onDismiss = onDismiss)
}
