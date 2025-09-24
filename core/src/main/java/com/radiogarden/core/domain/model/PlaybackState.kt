package com.radiogarden.core.domain.model

enum class PlaybackState {
    IDLE,
    BUFFERING,
    READY,
    PLAYING,
    PAUSED,
    STOPPED,
    ERROR
}

data class PlaybackInfo(
    val currentStation: RadioStation? = null,
    val state: PlaybackState = PlaybackState.IDLE,
    val position: Long = 0L,
    val duration: Long = 0L,
    val isShuffled: Boolean = false,
    val volume: Float = 1.0f,
    val error: String? = null
)
