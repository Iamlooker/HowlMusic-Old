package com.looker.howlmusic.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import com.looker.howlmusic.R
import com.looker.howlmusic.utils.Constants.artworkUri

object Bitmap {

    fun Long?.bitmap(context: Context): Bitmap {
        val uri = this?.artworkUri
        return when (val source =
            uri?.let { ImageDecoder.createSource(context.contentResolver, it) }) {
            null -> BitmapFactory.decodeResource(context.resources, R.drawable.empty)
            else -> ImageDecoder.decodeBitmap(source)
        }
    }
}