package com.nuagaon.telisahi.ui.features.dashboard

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(
    viewModel: DashboardViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "Nuagaon Telisahi",
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.titleLarge
                    )
                },
                actions = {
                    IconButton(onClick = { }) {
                        Icon(Icons.Rounded.Notifications, contentDescription = "Notifications")
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.Transparent
                )
            )
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { padding ->
        Box(modifier = Modifier.padding(padding)) {
            if (state.isLoading) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            } else {
                DashboardGrid(state)
            }
        }
    }
}

@Composable
fun DashboardGrid(state: DashboardState) {
    val items = listOf(
        DashboardItem("Total Houses", state.houseCount.toString(), Icons.Rounded.Home, Color(0xFFE3F2FD), Color(0xFF1976D2)),
        DashboardItem("Families", state.familyCount.toString(), Icons.Rounded.FamilyRestroom, Color(0xFFF3E5F5), Color(0xFF7B1FA2)),
        DashboardItem("Members", state.memberCount.toString(), Icons.Rounded.Person, Color(0xFFE8F5E9), Color(0xFF388E3C)),
        DashboardItem("Polisava", state.upcomingPolisava?.title ?: "No upcoming", Icons.Rounded.Event, Color(0xFFFFF3E0), Color(0xFFF57C00)),
        DashboardItem("Announcements", state.recentAnnouncements.size.toString(), Icons.Rounded.Announcement, Color(0xFFFFEBEE), Color(0xFFD32F2F)),
        DashboardItem("Schools", state.schoolCount.toString(), Icons.Rounded.School, Color(0xFFE0F2F1), Color(0xFF00796B)),
        DashboardItem("Gallery", state.imageCount.toString(), Icons.Rounded.PhotoLibrary, Color(0xFFFCE4EC), Color(0xFFC2185B)),
        DashboardItem("Videos", state.videoCount.toString(), Icons.Rounded.VideoLibrary, Color(0xFFFFFDE7), Color(0xFFFBC02D)),
        DashboardItem("Statistics", "View All", Icons.Rounded.BarChart, Color(0xFFF1F8E9), Color(0xFF689F38))
    )

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        item(span = { androidx.compose.foundation.lazy.grid.GridItemSpan(2) }) {
            WeatherWidget()
        }
        
        items(items) { item ->
            DashboardCard(item)
        }
    }
}

@Composable
fun DashboardCard(item: DashboardItem) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .clickable { },
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = item.bgColor)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(item.iconColor.copy(alpha = 0.1f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = item.icon,
                    contentDescription = item.title,
                    tint = item.iconColor,
                    modifier = Modifier.size(24.dp)
                )
            }
            
            Column {
                Text(
                    text = item.value,
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Text(
                    text = item.title,
                    style = MaterialTheme.typography.labelMedium,
                    color = Color.DarkGray
                )
            }
        }
    }
}

@Composable
fun WeatherWidget() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
    ) {
        Row(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text("28°C", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
                Text("Nuagaon, Partly Cloudy", style = MaterialTheme.typography.bodyMedium)
            }
            Icon(
                Icons.Rounded.WbCloudy,
                contentDescription = null,
                modifier = Modifier.size(48.dp),
                tint = MaterialTheme.colorScheme.primary
            )
        }
    }
}

data class DashboardItem(
    val title: String,
    val value: String,
    val icon: ImageVector,
    val bgColor: Color,
    val iconColor: Color
)
