package com.looker.howlmusic.ui.components

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
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.palette.graphics.Palette
import coil.ImageLoader
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import com.looker.howlmusic.utils.Constants.fadeInDuration
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun HowlImage(
    modifier: Modifier = Modifier,
    data: Uri?,
    size: Int = 250,
    shape: CornerBasedShape = MaterialTheme.shapes.medium,
    vibrantColor: (Color) -> Unit = {},
) {

    val defaultColor = MaterialTheme.colors.surface

    var extractedColor by remember {
        mutableStateOf(defaultColor)
    }

    val context = LocalContext.current

    val imageLoader = ImageLoader(context)

    val request = ImageRequest.Builder(context)
        .data(data = data)
        .size(size)
        .crossfade(fadeInDuration)
        .build()

    val imagePainter = rememberImagePainter(
        request = request,
        imageLoader = imageLoader
    )

    LaunchedEffect(key1 = imagePainter) {
        launch {
            val result = (imageLoader.execute(request)).drawable

            if (result != null) {
                extractedColor = result.getColorPalette()
            }
            vibrantColor(extractedColor)
        }
    }

    ImageDefault(modifier = modifier.clip(shape), painter = imagePainter)

}

@Composable
fun ImageDefault(
    modifier: Modifier = Modifier,
    painter: Painter,
) {
    Image(
        modifier = modifier,
        painter = painter,
        contentDescription = null
    )
}

private suspend fun Drawable.getColorPalette() =
    withContext(Dispatchers.IO) {
        val bmp =
            (this@getColorPalette as BitmapDrawable).bitmap.copy(Bitmap.Config.RGB_565, true)
        val dominant = Palette
            .from(bmp)
            .generate()
            .getDominantColor(0)
        val vibrant = Palette
            .from(bmp)
            .generate()
            .getVibrantColor(0)
        if (vibrant == 0) Color(dominant)
        else Color(vibrant)
    }