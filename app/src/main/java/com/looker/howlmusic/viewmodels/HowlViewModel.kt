package com.looker.howlmusic.viewmodels

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.DoneAll
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.looker.howlmusic.ui.theme.Green
import com.looker.howlmusic.ui.theme.OrangeDark

class HowlViewModel : ViewModel() {

    var buttonText by mutableStateOf("Grant Permission")

    var buttonIcon by mutableStateOf(Icons.Default.Close)

    var buttonColor by mutableStateOf(OrangeDark)

    fun onPermissionGranted() {
        buttonText = "Granted"
        buttonIcon = Icons.Default.DoneAll
        buttonColor = Green
    }

    fun onPermissionDenied() {
        buttonText = "Permission Denied"
        buttonIcon = Icons.Default.Cancel
        buttonColor = OrangeDark
    }

}