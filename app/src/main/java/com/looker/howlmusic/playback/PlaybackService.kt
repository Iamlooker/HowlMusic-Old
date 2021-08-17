package com.looker.howlmusic.playback

import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.media.MediaMetadata
import android.media.session.MediaSession
import android.net.Uri
import android.os.IBinder
import android.support.v4.media.session.MediaSessionCompat
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.ext.mediasession.MediaSessionConnector
import com.looker.howlmusic.data.SongsData
import com.looker.howlmusic.model.Song
import com.looker.howlmusic.utils.Constants.NOTIFICATION_ID

class PlaybackService : Service() {

    private var player: SimpleExoPlayer? = null
    var currentSong: Lazy<Song> = lazy { SongsData(this).getSongList()[0] }

    private lateinit var mediaSession: MediaSession
    private lateinit var mediaSessionConnector: MediaSessionConnector
    private lateinit var notification: PlaybackNotification
    private lateinit var notificationManager: NotificationManager

    override fun onCreate() {
        super.onCreate()

        val metadata by lazy {
            currentSong.value.songDurationMillis.let {
                MediaMetadata.Builder()
                    .putLong(MediaMetadata.METADATA_KEY_DURATION, it)
                    .build()
            }
        }

        mediaSession = MediaSession(this, "howlmusic")
        mediaSession.setMetadata(metadata)
        mediaSessionConnector =
            MediaSessionConnector(MediaSessionCompat.fromMediaSession(this, mediaSession))
        mediaSessionConnector.setPlayer(player)
        notificationManager =
            getSystemService(NotificationManager::class.java) as NotificationManager
        notification =
            PlaybackNotification.from(this, notificationManager, mediaSession, currentSong.value)
        startForeground(NOTIFICATION_ID, notification.build())
    }

    private fun initPlayer(exoPlayer: SimpleExoPlayer) {
        player = exoPlayer
    }

    fun initPlayer(exoPlayer: SimpleExoPlayer, mediaItems: ArrayList<MediaItem>) {
        initPlayer(exoPlayer)
        clearQueue()
        setMediaItems(mediaItems)
        prepare()
        play()
    }

    fun setMediaItems(mediaItems: ArrayList<MediaItem>) {
        player?.setMediaItems(mediaItems)
    }

    fun clearQueue() {
        player?.clearMediaItems()
    }

    fun setMediaItem(songUri: Uri) {
        player?.setMediaItem(MediaItem.fromUri(songUri))
    }

    fun prepare() {
        player?.prepare()
    }

    fun play() {
        player?.play()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int = START_NOT_STICKY
    override fun onBind(intent: Intent): IBinder? = null
    override fun onDestroy() {
        super.onDestroy()
        player?.release()
        mediaSession.release()
    }
}