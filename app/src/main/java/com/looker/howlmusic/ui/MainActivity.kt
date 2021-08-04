package com.looker.howlmusic.ui

import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.content.ContextCompat
import androidx.core.view.WindowCompat
import com.google.accompanist.insets.ProvideWindowInsets
import com.looker.howlmusic.HowlApp
import com.looker.howlmusic.utils.Constants.permission


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ProvideWindowInsets {
                HowlApp()
            }
        }

        WindowCompat.setDecorFitsSystemWindows(window, false)
    }

    private fun haveReadPermission() =
        ContextCompat.checkSelfPermission(
            this, permission[0]
        ) == PackageManager.PERMISSION_GRANTED

}