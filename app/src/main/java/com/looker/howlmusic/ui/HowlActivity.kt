package com.looker.howlmusic.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import com.google.accompanist.insets.ProvideWindowInsets
import com.looker.howlmusic.HowlApp
import com.looker.howlmusic.playback.PlaybackService

class HowlActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intent = Intent(this, PlaybackService::class.java)

        startForegroundService(intent)

        setContent {
            ProvideWindowInsets {
                HowlApp()
            }
        }
        WindowCompat.setDecorFitsSystemWindows(window, false)
    }
}