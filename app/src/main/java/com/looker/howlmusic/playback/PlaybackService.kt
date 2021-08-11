package com.looker.howlmusic.playback

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.NotificationManager.IMPORTANCE_HIGH
import android.app.Service
import android.content.Context
import android.content.Intent
import android.media.MediaMetadata
import android.media.session.MediaSession
import android.os.IBinder
import androidx.core.net.toUri
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.RenderersFactory
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.audio.MediaCodecAudioRenderer
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory
import com.google.android.exoplayer2.mediacodec.MediaCodecSelector
import com.google.android.exoplayer2.source.DefaultMediaSourceFactory
import com.looker.howlmusic.R
import com.looker.howlmusic.data.SongsData
import com.looker.howlmusic.model.Song
import com.looker.howlmusic.utils.Constants.NOTIFICATION_CHANNEL_ID
import com.looker.howlmusic.utils.Constants.NOTIFICATION_CHANNEL_NAME
import com.looker.howlmusic.utils.Constants.NOTIFICATION_ID
import com.looker.howlmusic.utils.Constants.artworkUri


class PlaybackService : Service() {

    lateinit var player: SimpleExoPlayer
    val sampleSong: Song = Song(songUri = "content://media/external/audio/media/809".toUri(),
        albumId = 1472833616896716278,
        songName = "PLAYING WITH FIRE",
        artistName = "BLACKPINK",
        songDurationMillis = 197329)

    override fun onCreate() {
        super.onCreate()
        player = newPlayer()
        val songsList = SongsData(this).getSongList()
        val mediaItem: ArrayList<MediaItem> = arrayListOf()
        songsList.forEach {
            mediaItem.add(MediaItem.fromUri(it.songUri))
        }
        player.setMediaItems(mediaItem)
        player.prepare()
        startForegroundService()
    }

    override fun onDestroy() {
        super.onDestroy()
        player.release()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int = START_NOT_STICKY

    override fun onBind(intent: Intent): IBinder? = null

    private fun startForegroundService() {
        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        createNotificationChannel(notificationManager)
        val mediaSession = MediaSession(this, "PlayerService")
        val mediaStyle = Notification.MediaStyle().setMediaSession(mediaSession.sessionToken)

        mediaSession.setMetadata(
            MediaMetadata.Builder()
                .putString(MediaMetadata.METADATA_KEY_TITLE, sampleSong.songName)
                .putString(MediaMetadata.METADATA_KEY_ALBUM_ART_URI,
                    sampleSong.albumId.artworkUri.toString())
                .putString(MediaMetadata.METADATA_KEY_ARTIST, sampleSong.artistName)
                .putLong(MediaMetadata.METADATA_KEY_DURATION,
                    sampleSong.songDurationMillis.toLong())
                .build()
        )

        val notificationBuilder = Notification.Builder(this, NOTIFICATION_CHANNEL_ID)
            .setStyle(mediaStyle)
            .setSmallIcon(R.drawable.ic_launcher_foreground)

        startForeground(NOTIFICATION_ID, notificationBuilder.build())
    }


    private fun createNotificationChannel(notificationManager: NotificationManager) {
        val channel = NotificationChannel(
            NOTIFICATION_CHANNEL_ID,
            NOTIFICATION_CHANNEL_NAME,
            IMPORTANCE_HIGH
        )
        notificationManager.createNotificationChannel(channel)
    }

    private fun newPlayer(): SimpleExoPlayer {
        val audioRenderer = RenderersFactory { handler, _, audioListener, _, _ ->
            arrayOf(
                MediaCodecAudioRenderer(this, MediaCodecSelector.DEFAULT, handler, audioListener)
            )
        }
        val extractorsFactory = DefaultExtractorsFactory().setConstantBitrateSeekingEnabled(true)

        return SimpleExoPlayer.Builder(this, audioRenderer)
            .setMediaSourceFactory(DefaultMediaSourceFactory(this, extractorsFactory))
            .build()
    }
}