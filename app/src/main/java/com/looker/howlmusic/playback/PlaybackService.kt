package com.looker.howlmusic.playback

import android.app.*
import android.app.NotificationManager.IMPORTANCE_HIGH
import android.app.PendingIntent.FLAG_ONE_SHOT
import android.content.Context
import android.content.Intent
import android.graphics.drawable.Icon
import android.media.MediaMetadata
import android.media.session.MediaSession
import android.os.IBinder
import com.google.android.exoplayer2.RenderersFactory
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.audio.MediaCodecAudioRenderer
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory
import com.google.android.exoplayer2.mediacodec.MediaCodecSelector
import com.google.android.exoplayer2.source.DefaultMediaSourceFactory
import com.looker.howlmusic.R
import com.looker.howlmusic.data.SongsData
import com.looker.howlmusic.utils.Constants.NOTIFICATION_CHANNEL_ID
import com.looker.howlmusic.utils.Constants.NOTIFICATION_CHANNEL_NAME
import com.looker.howlmusic.utils.Constants.NOTIFICATION_ID
import com.looker.howlmusic.utils.Constants.artworkUri


class PlaybackService : Service() {

    private lateinit var player: SimpleExoPlayer

    private lateinit var notification: Notification
    override fun onCreate() {
        super.onCreate()
        player = newPlayer()
        startForegroundService()
    }

    private val sampleSong by lazy { SongsData(this).getSongList()[0] }

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

        notification = Notification.Builder(this, NOTIFICATION_CHANNEL_ID)
            .setStyle(mediaStyle)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .build()

        val pendingIntent = PendingIntent.getService(
            this,
            NOTIFICATION_ID,
            Intent(),
            FLAG_ONE_SHOT
        )

        val playAction = Notification.Action.Builder(
            Icon.createWithResource(this, R.drawable.ic_play),
            "play",
            pendingIntent
        ).build()

        val nextAction = Notification.Action.Builder(
            Icon.createWithResource(this, R.drawable.ic_next),
            "play",
            pendingIntent
        ).build()

        val previousAction = Notification.Action.Builder(
            Icon.createWithResource(this, R.drawable.ic_previous),
            "play",
            pendingIntent
        ).build()

        notification.actions = arrayOf(previousAction, playAction, nextAction)

        startForeground(NOTIFICATION_ID, notification)
    }


    private fun createNotificationChannel(notificationManager: NotificationManager) {
        val channel = NotificationChannel(
            NOTIFICATION_CHANNEL_ID,
            NOTIFICATION_CHANNEL_NAME,
            IMPORTANCE_HIGH
        )
        notificationManager.createNotificationChannel(channel)
    }

    override fun onDestroy() {
        super.onDestroy()
        player.release()
    }
}