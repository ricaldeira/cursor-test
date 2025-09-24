package com.carmobileapp.mobile.presentation.navigation

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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationScreen(
    onNavigateBack: () -> Unit,
    viewModel: NavigationViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Navigation") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Card(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text(
                            "Quick Destinations",
                            style = MaterialTheme.typography.headlineSmall,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                IconButton(
                                    onClick = { /* TODO: Navigate to home */ }
                                ) {
                                    Icon(Icons.Default.Home, contentDescription = "Home")
                                }
                                Text("Home", style = MaterialTheme.typography.bodySmall)
                            }
                            
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                IconButton(
                                    onClick = { /* TODO: Navigate to work */ }
                                ) {
                                    Icon(Icons.Default.Work, contentDescription = "Work")
                                }
                                Text("Work", style = MaterialTheme.typography.bodySmall)
                            }
                            
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                IconButton(
                                    onClick = { /* TODO: Navigate to favorites */ }
                                ) {
                                    Icon(Icons.Default.Favorite, contentDescription = "Favorites")
                                }
                                Text("Favorites", style = MaterialTheme.typography.bodySmall)
                            }
                        }
                    }
                }
            }
            
            item {
                OutlinedTextField(
                    value = uiState.searchQuery,
                    onValueChange = viewModel::updateSearchQuery,
                    label = { Text("Search destination") },
                    leadingIcon = {
                        Icon(Icons.Default.Search, contentDescription = "Search")
                    },
                    modifier = Modifier.fillMaxWidth()
                )
            }
            
            if (uiState.searchResults.isNotEmpty()) {
                items(uiState.searchResults) { location ->
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = { /* TODO: Start navigation to location */ }
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp)
                        ) {
                            Text(
                                location.name ?: "Unknown Location",
                                style = MaterialTheme.typography.bodyLarge,
                                fontWeight = FontWeight.Medium
                            )
                            if (location.address != null) {
                                Text(
                                    location.address,
                                    style = MaterialTheme.typography.bodyMedium
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}