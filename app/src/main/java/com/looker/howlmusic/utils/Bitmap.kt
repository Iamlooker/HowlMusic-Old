package com.looker.howlmusic.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import com.looker.howlmusic.utils.Constants.artworkUri

object Bitmap {

    fun Long?.bitmap(context: Context): Bitmap {
        val uri = this!!.artworkUri
        val source = ImageDecoder.createSource(context.contentResolver, uri)
        return ImageDecoder.decodeBitmap(source)
    }
}