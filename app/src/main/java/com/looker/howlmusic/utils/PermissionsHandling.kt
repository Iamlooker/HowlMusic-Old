package com.looker.howlmusic.utils

import android.content.Context
import android.content.pm.PackageManager
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.core.content.ContextCompat

fun checkReadPermission(context: Context) =
    ContextCompat.checkSelfPermission(
        context, Constants.permission[0]
    ) == PackageManager.PERMISSION_GRANTED

fun askReadPermission(launcher: ManagedActivityResultLauncher<String, Boolean>) {
    launcher.launch(Constants.permission[0])
}

fun handlePermissions(
    context: Context,
    launcher: ManagedActivityResultLauncher<String, Boolean>,
    onGranted: () -> Unit,
    onDenied: () -> Unit,
) {
    if (checkReadPermission(context)) {
        onGranted()
    } else {
        onDenied()
        askReadPermission(launcher)
    }
}