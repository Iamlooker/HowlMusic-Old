package com.looker.howlmusic.ui

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import com.google.accompanist.insets.ProvideWindowInsets
import com.looker.howlmusic.HowlApp
import com.looker.howlmusic.playback.PlaybackService
import com.looker.howlmusic.utils.Constants.permission

class HowlActivity : ComponentActivity() {
    val playerService = PlaybackService()
    var isPlaying = true

    override fun onCreate(savedInstanceState: Bundle?) {
        val intent = Intent(this, playerService::class.java)
        super.onCreate(savedInstanceState)
        val havePermission = checkSelfPermission(permission[0]) == PackageManager.PERMISSION_GRANTED

        startNotification(isPlaying, havePermission, intent)

        setContent {
            ProvideWindowInsets {
                HowlApp()
            }
        }
        WindowCompat.setDecorFitsSystemWindows(window, false)
    }

    fun startNotification(isPlaying: Boolean, hasPermission: Boolean, intent: Intent) {
        if (isPlaying && hasPermission) startForegroundService(intent)
    }
}