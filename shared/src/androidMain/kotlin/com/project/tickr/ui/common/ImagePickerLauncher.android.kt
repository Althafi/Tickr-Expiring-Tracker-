package com.project.tickr.ui.common

import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

@Composable
actual fun rememberImagePickerLauncher(
    onImagePicked: (uri: String?) -> Unit,
): () -> Unit {
    // PickVisualMedia (API 33+) → no explicit permission needed (system picker)
    // GetContent (API 26-32) → READ_EXTERNAL_STORAGE declared in manifest
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        val launcher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.PickVisualMedia(),
            onResult = { uri -> onImagePicked(uri?.toString()) },
        )
        remember { { launcher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)) } }
    } else {
        val launcher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.GetContent(),
            onResult = { uri -> onImagePicked(uri?.toString()) },
        )
        remember { { launcher.launch("image/*") } }
    }
}
