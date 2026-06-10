package com.project.tickr.ui.common

import androidx.compose.runtime.Composable

@Composable
actual fun rememberImagePickerLauncher(
    onImagePicked: (uri: String?) -> Unit,
): () -> Unit {
    // TODO(user): implement with PHPickerViewController via UIKitView
    return {}
}
