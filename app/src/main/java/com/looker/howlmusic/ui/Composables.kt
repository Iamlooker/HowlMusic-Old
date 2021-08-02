package com.looker.howlmusic.ui

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.palette.graphics.Palette
import coil.ImageLoader
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import coil.transform.RoundedCornersTransformation
import kotlinx.coroutines.launch

@Composable
fun AlbumsArt(
    modifier: Modifier = Modifier,
    data: Uri?,
    shape: CornerBasedShape = MaterialTheme.shapes.medium,
    content: (Color) -> Unit = {},
) {
    val defaultColor = MaterialTheme.colors.surface
    var dominantColor by remember {
        mutableStateOf(defaultColor)
    }
    val context = LocalContext.current

    val imageLoader = ImageLoader(context)

    val request = ImageRequest.Builder(context)
        .transformations(RoundedCornersTransformation(12.dp.value))
        .data(data = data)
        .build()

    val imagePainter = rememberImagePainter(
        request = request,
        imageLoader = imageLoader
    )

    LaunchedEffect(key1 = imagePainter) {
        launch {
            val result = (imageLoader.execute(request)).drawable

            if (result != null) {
                dominantColor = result.calcDominantColor().copy(0.4f)
            }
            content(dominantColor)
        }
    }

    Image(
        painter = imagePainter,
        contentDescription = data.toString(),
        modifier = modifier.clip(shape)
    )
}


fun Drawable.calcDominantColor(): Color {
    val bmp = (this as BitmapDrawable).bitmap.copy(Bitmap.Config.ARGB_8888, true)
    val vibrant: Int = Palette
        .from(bmp)
        .generate()
        .getVibrantColor(0)
    return Color(vibrant)
}