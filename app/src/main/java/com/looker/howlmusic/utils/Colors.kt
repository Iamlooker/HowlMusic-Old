package com.looker.howlmusic.utils

import android.content.Context
import androidx.collection.LruCache
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.core.graphics.drawable.toBitmap
import androidx.palette.graphics.Palette
import coil.Coil
import coil.request.ImageRequest
import coil.size.Scale

@Composable
fun rememberDominantColorState(
    context: Context = LocalContext.current,
    defaultColor: Color = MaterialTheme.colors.surface,
    defaultOnColor: Color = MaterialTheme.colors.onSurface,
    cacheSize: Int = 12,
): DominantColorState = remember {
    DominantColorState(context, defaultColor, defaultOnColor, cacheSize)
}

@Stable
class DominantColorState(
    private val context: Context,
    defaultColor: Color,
    defaultOnColor: Color,
    cacheSize: Int = 50,
) {
    var color by mutableStateOf(defaultColor)
        private set
    var onColor by mutableStateOf(defaultOnColor)
        private set

    private val cache = when {
        cacheSize > 0 -> LruCache<String, DominantColors>(cacheSize)
        else -> null
    }

    suspend fun updateColorsFromImageUrl(url: String) {
        val result = calculateDominantColor(url)
        color = result.color
        onColor = result.onColor
    }

    private suspend fun calculateDominantColor(url: String): DominantColors {
        return cache?.get(url) ?: DominantColors(
            color = calculatePrimaryColorInImage(context, url),
            onColor = calculatePrimaryColorInImage(context, url).copy(alpha = 0.4f)
        )
            .also { result -> cache?.put(url, result) }
    }
}

@Immutable
private data class DominantColors(val color: Color, val onColor: Color)


private suspend fun calculatePrimaryColorInImage(
    context: Context,
    imageUrl: String,
): Color {
    val r = ImageRequest.Builder(context)
        .data(imageUrl)
        .size(128).scale(Scale.FILL)
        .allowHardware(false)
        .build()

    val result = Coil.execute(r)

    val bitmap = result.drawable?.toBitmap()

    val swatch = Palette.Builder(bitmap!!)
        .resizeBitmapArea(0)
        .clearFilters()
        .maximumColorCount(8)
        .generate()

    val vibrant = swatch.getVibrantColor(0)
    val dominant = swatch.getDominantColor(0)

    return if (vibrant == 0) Color(dominant)
    else Color(vibrant)
}
