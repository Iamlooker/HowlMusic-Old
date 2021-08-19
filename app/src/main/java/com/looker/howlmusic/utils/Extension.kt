package com.looker.howlmusic.utils

import android.app.Service
import android.content.Context
import android.content.Intent
import android.net.Uri

object Extension {
    val Long.artworkUri: Uri?
        get() = Uri.parse("content://media/external/audio/albumart/$this")

    val Long.pathUri: Uri
        get() = Uri.parse("${Constants.externalUri}/$this")

    fun Context.startNotification(show: Boolean, service: Service) {
        if (show) startForegroundService(Intent(this, service::class.java))
    }
}