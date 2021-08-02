package com.looker.howlmusic.viewModels

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.lifecycle.ViewModel
import com.looker.howlmusic.R

class HowlViewModel : ViewModel() {

    val text: MutableState<String> = mutableStateOf("Grant Permission")

    val icon: MutableState<ImageVector> = mutableStateOf(Icons.Default.Close)

    val color: MutableState<Int> = mutableStateOf(R.color.orange)

    val enabled: MutableState<Boolean> = mutableStateOf(true)
}