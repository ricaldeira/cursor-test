package com.radiogarden.core.audio

import android.content.Context
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.session.MediaSession
import androidx.media3.session.MediaSessionService
import com.radiogarden.core.domain.model.RadioStation
import com.radiogarden.core.domain.model.PlaybackState
import com.radiogarden.core.domain.model.PlaybackInfo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AudioStreamingService @Inject constructor(
    private val context: Context
) {
    private var exoPlayer: ExoPlayer? = null
    private var mediaSession: MediaSession? = null
    
    private val _playbackInfo = MutableStateFlow(PlaybackInfo())
    val playbackInfo: StateFlow<PlaybackInfo> = _playbackInfo.asStateFlow()
    
    private val playerListener = object : Player.Listener {
        override fun onPlaybackStateChanged(playbackState: Int) {
            val state = when (playbackState) {
                Player.STATE_IDLE -> PlaybackState.IDLE
                Player.STATE_BUFFERING -> PlaybackState.BUFFERING
                Player.STATE_READY -> PlaybackState.READY
                Player.STATE_ENDED -> PlaybackState.STOPPED
                else -> PlaybackState.IDLE
            }
            
            _playbackInfo.value = _playbackInfo.value.copy(
                state = state,
                error = if (playbackState == Player.STATE_IDLE) "Playback error" else null
            )
        }
        
        override fun onIsPlayingChanged(isPlaying: Boolean) {
            val currentState = _playbackInfo.value.state
            val newState = if (isPlaying) PlaybackState.PLAYING else PlaybackState.PAUSED
            
            _playbackInfo.value = _playbackInfo.value.copy(state = newState)
        }
        
        override fun onPlayerError(error: androidx.media3.common.PlaybackException) {
            _playbackInfo.value = _playbackInfo.value.copy(
                state = PlaybackState.ERROR,
                error = error.message
            )
        }
    }
    
    fun initialize() {
        if (exoPlayer == null) {
            exoPlayer = ExoPlayer.Builder(context).build().apply {
                addListener(playerListener)
            }
        }
        
        if (mediaSession == null) {
            mediaSession = MediaSession.Builder(context, exoPlayer!!).build()
        }
    }
    
    fun playStation(station: RadioStation) {
        initialize()
        
        val mediaItem = MediaItem.Builder()
            .setUri(station.streamUrl)
            .setMediaId(station.id)
            .setMediaMetadata(
                androidx.media3.common.MediaMetadata.Builder()
                    .setTitle(station.name)
                    .setArtist(station.location)
                    .setArtworkUri(android.net.Uri.parse(station.imageUrl ?: ""))
                    .build()
            )
            .build()
        
        exoPlayer?.setMediaItem(mediaItem)
        exoPlayer?.prepare()
        exoPlayer?.play()
        
        _playbackInfo.value = _playbackInfo.value.copy(
            currentStation = station,
            state = PlaybackState.BUFFERING
        )
    }
    
    fun pause() {
        exoPlayer?.pause()
    }
    
    fun resume() {
        exoPlayer?.play()
    }
    
    fun stop() {
        exoPlayer?.stop()
        _playbackInfo.value = _playbackInfo.value.copy(
            currentStation = null,
            state = PlaybackState.STOPPED
        )
    }
    
    fun setVolume(volume: Float) {
        exoPlayer?.volume = volume
        _playbackInfo.value = _playbackInfo.value.copy(volume = volume)
    }
    
    fun release() {
        exoPlayer?.removeListener(playerListener)
        exoPlayer?.release()
        exoPlayer = null
        
        mediaSession?.run {
            player.release()
            release()
        }
        mediaSession = null
    }
    
    fun getCurrentPosition(): Long = exoPlayer?.currentPosition ?: 0L
    
    fun getDuration(): Long = exoPlayer?.duration ?: 0L
    
    fun isPlaying(): Boolean = exoPlayer?.isPlaying ?: false
}
