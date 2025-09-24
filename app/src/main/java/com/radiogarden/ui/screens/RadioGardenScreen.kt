package com.radiogarden.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.radiogarden.ui.components.RadioStationCard
import com.radiogarden.ui.components.PlaybackControls
import com.radiogarden.ui.viewmodel.RadioGardenViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RadioGardenScreen(
    viewModel: RadioGardenViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val playbackInfo by viewModel.playbackInfo.collectAsState()
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Radio Garden") },
                actions = {
                    IconButton(onClick = { viewModel.toggleSearch() }) {
                        Icon(
                            imageVector = if (uiState.isSearchVisible) Icons.Default.Close else Icons.Default.Search,
                            contentDescription = "Search"
                        )
                    }
                }
            )
        },
        bottomBar = {
            if (playbackInfo.currentStation != null) {
                PlaybackControls(
                    playbackInfo = playbackInfo,
                    onPlayPause = { viewModel.togglePlayback() },
                    onStop = { viewModel.stopPlayback() }
                )
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            // Search Bar
            if (uiState.isSearchVisible) {
                OutlinedTextField(
                    value = uiState.searchQuery,
                    onValueChange = viewModel::updateSearchQuery,
                    label = { Text("Search stations...") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    leadingIcon = {
                        Icon(Icons.Default.Search, contentDescription = null)
                    }
                )
            }
            
            // Tab Row
            TabRow(selectedTabIndex = uiState.selectedTabIndex) {
                Tab(
                    selected = uiState.selectedTabIndex == 0,
                    onClick = { viewModel.selectTab(0) },
                    text = { Text("All Stations") },
                    icon = { Icon(Icons.Default.Radio, contentDescription = null) }
                )
                Tab(
                    selected = uiState.selectedTabIndex == 1,
                    onClick = { viewModel.selectTab(1) },
                    text = { Text("Favorites") },
                    icon = { Icon(Icons.Default.Favorite, contentDescription = null) }
                )
                Tab(
                    selected = uiState.selectedTabIndex == 2,
                    onClick = { viewModel.selectTab(2) },
                    text = { Text("Recent") },
                    icon = { Icon(Icons.Default.History, contentDescription = null) }
                )
            }
            
            // Content
            when {
                uiState.isLoading -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
                uiState.error != null -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "Error: ${uiState.error}",
                                style = MaterialTheme.typography.bodyLarge,
                                color = MaterialTheme.colorScheme.error
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            Button(onClick = { viewModel.retry() }) {
                                Text("Retry")
                            }
                        }
                    }
                }
                else -> {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(16.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(uiState.stations) { station ->
                            RadioStationCard(
                                station = station,
                                isPlaying = playbackInfo.currentStation?.id == station.id,
                                onPlayClick = { viewModel.playStation(station) },
                                onFavoriteClick = { viewModel.toggleFavorite(station.id) }
                            )
                        }
                    }
                }
            }
        }
    }
}
