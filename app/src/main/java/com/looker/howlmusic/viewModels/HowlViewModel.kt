package com.looker.howlmusic.viewModels

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.lifecycle.ViewModel
import com.looker.howlmusic.R

class HowlViewModel : ViewModel() {

    val buttonText: MutableState<String> = mutableStateOf("Grant Permission")

    val buttonIcon: MutableState<ImageVector> = mutableStateOf(Icons.Default.Close)

    val buttonColor: MutableState<Int> = mutableStateOf(R.color.orange)

}