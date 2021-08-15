package com.looker.howlmusic.playback

import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.media.MediaMetadata
import android.media.session.MediaSession
import android.os.IBinder
import android.support.v4.media.session.MediaSessionCompat
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.ext.mediasession.MediaSessionConnector
import com.looker.howlmusic.data.SongsData
import com.looker.howlmusic.utils.Constants.NOTIFICATION_ID

class PlaybackService : Service() {

    lateinit var player: SimpleExoPlayer

    private lateinit var mediaSession: MediaSession
    private lateinit var mediaSessionConnector: MediaSessionConnector
    private lateinit var notification: PlaybackNotification
    private lateinit var notificationManager: NotificationManager

    override fun onCreate() {
        super.onCreate()

        val currentSong by lazy { SongsData(this).getSongList()[0] }
        val metadata by lazy {
            MediaMetadata.Builder()
                .putLong(MediaMetadata.METADATA_KEY_DURATION,
                    currentSong.songDurationMillis.toLong())
                .build()
        }

        player = SimpleExoPlayer.Builder(this).build()
        player.clearMediaItems()
        player.setMediaItem(MediaItem.fromUri(currentSong.songUri))
        player.prepare()
        player.play()

        mediaSession = MediaSession(this, "howlmusic")
        mediaSession.setMetadata(metadata)
        mediaSessionConnector =
            MediaSessionConnector(MediaSessionCompat.fromMediaSession(this, mediaSession))
        mediaSessionConnector.setPlayer(player)
        notificationManager =
            getSystemService(NotificationManager::class.java) as NotificationManager
        notification =
            PlaybackNotification.from(this, notificationManager, mediaSession, currentSong)
        startForeground(NOTIFICATION_ID, notification.build())
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int = START_NOT_STICKY

    override fun onBind(intent: Intent): IBinder? = null

    override fun onDestroy() {
        super.onDestroy()
        player.release()
        mediaSession.release()
    }
}