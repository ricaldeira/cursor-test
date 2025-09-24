package com.radiogarden.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.radiogarden.core.domain.model.PlaybackInfo
import com.radiogarden.core.domain.model.PlaybackState

@Composable
fun PlaybackControls(
    playbackInfo: PlaybackInfo,
    onPlayPause: () -> Unit,
    onStop: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Station Info
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = playbackInfo.currentStation?.name ?: "No station",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = playbackInfo.currentStation?.location ?: "",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
            
            // Controls
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // Play/Pause Button
                IconButton(
                    onClick = onPlayPause,
                    modifier = Modifier.size(48.dp)
                ) {
                    Icon(
                        imageVector = when (playbackInfo.state) {
                            PlaybackState.PLAYING -> Icons.Default.Pause
                            PlaybackState.BUFFERING -> Icons.Default.HourglassEmpty
                            else -> Icons.Default.PlayArrow
                        },
                        contentDescription = when (playbackInfo.state) {
                            PlaybackState.PLAYING -> "Pause"
                            PlaybackState.BUFFERING -> "Buffering"
                            else -> "Play"
                        },
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(32.dp)
                    )
                }
                
                // Stop Button
                IconButton(onClick = onStop) {
                    Icon(
                        imageVector = Icons.Default.Stop,
                        contentDescription = "Stop",
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
        
        // Progress Bar (if available)
        if (playbackInfo.duration > 0) {
            LinearProgressIndicator(
                progress = { playbackInfo.position.toFloat() / playbackInfo.duration.toFloat() },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            )
        }
    }
}
