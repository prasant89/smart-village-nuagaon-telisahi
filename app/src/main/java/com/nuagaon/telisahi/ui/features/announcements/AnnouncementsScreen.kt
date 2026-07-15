package com.nuagaon.telisahi.ui.features.announcements

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Campaign
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material.icons.rounded.PriorityHigh
import androidx.compose.material.icons.rounded.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.nuagaon.telisahi.domain.model.Announcement
import com.nuagaon.telisahi.domain.model.AnnouncementCategory
import com.nuagaon.telisahi.domain.model.AnnouncementPriority
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnnouncementsScreen(
    viewModel: AnnouncementsViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            LargeTopAppBar(
                title = { Text("Announcements", fontWeight = FontWeight.Bold) },
                colors = TopAppBarDefaults.largeTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                )
            )
        }
    ) { padding ->
        Box(modifier = Modifier.fillMaxSize().padding(padding)) {
            if (state.isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            } else {
                AnnouncementsList(state.announcements)
            }
        }
    }
}

@Composable
fun AnnouncementsList(announcements: List<Announcement>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(announcements) { announcement ->
            AnnouncementCard(announcement)
        }
    }
}

@Composable
fun AnnouncementCard(announcement: Announcement) {
    val dateFormat = SimpleDateFormat("dd MMM, yyyy", Locale.getDefault())
    val categoryColor = when (announcement.category) {
        AnnouncementCategory.EMERGENCY -> MaterialTheme.colorScheme.error
        AnnouncementCategory.HEALTH -> Color(0xFF4CAF50)
        AnnouncementCategory.EDUCATION -> Color(0xFF2196F3)
        AnnouncementCategory.DEVELOPMENT -> Color(0xFFFF9800)
        else -> MaterialTheme.colorScheme.secondary
    }

    val icon = when (announcement.priority) {
        AnnouncementPriority.URGENT -> Icons.Rounded.PriorityHigh
        AnnouncementPriority.HIGH -> Icons.Rounded.Warning
        else -> Icons.Rounded.Campaign
    }

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.Top
        ) {
            Surface(
                color = categoryColor.copy(alpha = 0.1f),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.size(48.dp)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        tint = categoryColor
                    )
                }
            }
            
            Spacer(modifier = Modifier.width(16.dp))
            
            Column(modifier = Modifier.weight(1f)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = announcement.category.name,
                        style = MaterialTheme.typography.labelSmall,
                        color = categoryColor,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = dateFormat.format(announcement.date),
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.outline
                    )
                }
                
                Spacer(modifier = Modifier.height(4.dp))
                
                Text(
                    text = announcement.title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                
                Spacer(modifier = Modifier.height(4.dp))
                
                Text(
                    text = announcement.content,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}
