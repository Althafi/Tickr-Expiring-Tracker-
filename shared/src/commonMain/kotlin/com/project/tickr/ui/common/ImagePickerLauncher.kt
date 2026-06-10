package com.project.tickr.ui.common

import androidx.compose.runtime.Composable

/** Returns a lambda; ketika dipanggil, membuka gallery picker platform (dengan permission). */
@Composable
expect fun rememberImagePickerLauncher(
    onImagePicked: (uri: String?) -> Unit,
): () -> Unit
