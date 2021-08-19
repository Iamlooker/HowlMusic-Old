package com.looker.howlmusic.utils

import android.content.Context
import android.graphics.BitmapFactory
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
import com.looker.howlmusic.R

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
    private val defaultColor: Color,
    private val defaultOnColor: Color,
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
        color = result?.color ?: defaultColor
        onColor = result?.onColor ?: defaultOnColor
    }

    private suspend fun calculateDominantColor(url: String): DominantColors? {
        return cache?.get(url) ?: calculatePrimaryColorInImage(context, url)?.let { dominantColor ->
            DominantColors(
                color = dominantColor,
                onColor = dominantColor.copy(alpha = 0.4f)
            )
                .also { result -> cache?.put(url, result) }
        }
    }
}

@Immutable
private data class DominantColors(val color: Color, val onColor: Color)


private suspend fun calculatePrimaryColorInImage(
    context: Context,
    imageUrl: String,
): Color? {
    val r = ImageRequest.Builder(context)
        .data(imageUrl)
        .size(128).scale(Scale.FILL)
        .allowHardware(false)
        .build()

    val result = Coil.execute(r)

    val bitmap = result.drawable?.toBitmap() ?: BitmapFactory.decodeResource(context.resources,
        R.drawable.empty)

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
