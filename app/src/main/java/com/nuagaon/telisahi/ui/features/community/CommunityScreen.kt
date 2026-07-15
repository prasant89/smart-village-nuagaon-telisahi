package com.nuagaon.telisahi.ui.features.community

import androidx.compose.animation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.Engineering
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.nuagaon.telisahi.domain.model.DevelopmentProject
import com.nuagaon.telisahi.domain.model.VillageMember

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommunityScreen(
    viewModel: CommunityViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Community & Records", fontWeight = FontWeight.Bold) }
            )
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            TabRow(
                selectedTabIndex = state.selectedTab,
                containerColor = MaterialTheme.colorScheme.background,
                divider = {}
            ) {
                Tab(
                    selected = state.selectedTab == 0,
                    onClick = { viewModel.onEvent(CommunityEvent.OnTabSelected(0)) },
                    text = { Text("Members") }
                )
                Tab(
                    selected = state.selectedTab == 1,
                    onClick = { viewModel.onEvent(CommunityEvent.OnTabSelected(1)) },
                    text = { Text("Development") }
                )
            }

            Box(modifier = Modifier.fillMaxSize()) {
                if (state.isLoading) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                } else {
                    AnimatedContent(
                        targetState = state.selectedTab,
                        label = "tab_transition",
                        transitionSpec = {
                            fadeIn() togetherWith fadeOut()
                        }
                    ) { tabIndex ->
                        if (tabIndex == 0) {
                            MembersList(state.members)
                        } else {
                            ProjectsList(state.projects)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun MembersList(members: List<VillageMember>) {
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(members) { member ->
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f))
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        Icons.Rounded.AccountCircle,
                        contentDescription = null,
                        modifier = Modifier.size(40.dp),
                        tint = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Column {
                        Text(text = member.name, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                        Text(text = "${member.role} • ${member.area}", style = MaterialTheme.typography.bodySmall)
                    }
                }
            }
        }
    }
}

@Composable
fun ProjectsList(projects: List<DevelopmentProject>) {
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(projects) { project ->
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(24.dp)
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Rounded.Engineering, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(text = project.category.name, style = MaterialTheme.typography.labelLarge, color = MaterialTheme.colorScheme.primary)
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = project.title, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                    Text(text = project.description, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(text = "Budget: ${project.budget}", style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(8.dp))
                    LinearProgressIndicator(
                        progress = { project.progress },
                        modifier = Modifier.fillMaxWidth().height(8.dp),
                        strokeCap = androidx.compose.ui.graphics.StrokeCap.Round
                    )
                }
            }
        }
    }
}
