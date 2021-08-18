package com.looker.howlmusic.ui.components

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import coil.compose.rememberImagePainter
import com.looker.howlmusic.R
import com.looker.howlmusic.utils.Constants.fadeInDuration

@Composable
fun HowlImage(
    modifier: Modifier = Modifier,
    data: Uri?,
    size: Int = 600,
    shape: CornerBasedShape = MaterialTheme.shapes.medium,
) {
    Image(
        modifier = modifier.clip(shape),
        painter = rememberImagePainter(
            data = data,
            builder = {
                size(size)
                error(R.drawable.empty)
                crossfade(fadeInDuration)
            }
        ),
        contentDescription = null
    )
}