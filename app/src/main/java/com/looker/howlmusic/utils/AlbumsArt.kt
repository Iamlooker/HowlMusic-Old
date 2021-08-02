package com.looker.howlmusic.utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.MediaMetadataRetriever
import android.util.Log

object AlbumsArt {
    fun getAlbumArtByte(path: String?): Bitmap? {
        val retriever = MediaMetadataRetriever()
        try {
            retriever.setDataSource(path)
        } catch (e: NullPointerException) {
            Log.e("Adapter", e.message.toString())
        }
        val imgByte = retriever.embeddedPicture
        retriever.release()
        return convertByteArrayToBitmap(imgByteArray = imgByte)
    }

    private fun convertByteArrayToBitmap(imgByteArray: ByteArray?): Bitmap? {
        return imgByteArray?.let { BitmapFactory.decodeByteArray(imgByteArray, 0, it.size) }
    }
}