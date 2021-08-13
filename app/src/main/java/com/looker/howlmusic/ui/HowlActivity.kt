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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intent = Intent(this, PlaybackService::class.java)

        if (checkSelfPermission(permission[0]) == PackageManager.PERMISSION_GRANTED) {
            startForegroundService(intent)
        }

        setContent {
            ProvideWindowInsets {
                HowlApp()
            }
        }
        WindowCompat.setDecorFitsSystemWindows(window, false)
    }
}