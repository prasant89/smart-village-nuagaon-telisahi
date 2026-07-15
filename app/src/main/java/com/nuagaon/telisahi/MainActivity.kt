package com.nuagaon.telisahi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.Announcement
import androidx.compose.material.icons.rounded.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.nuagaon.telisahi.ui.features.announcements.AnnouncementsScreen
import com.nuagaon.telisahi.ui.features.community.CommunityScreen
import com.nuagaon.telisahi.ui.features.dashboard.DashboardScreen
import com.nuagaon.telisahi.ui.features.meetings.MeetingsScreen
import com.nuagaon.telisahi.ui.navigation.Screen
import com.nuagaon.telisahi.ui.theme.NuagaonTelisahiTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NuagaonTelisahiTheme {
                MainScreen()
            }
        }
    }
}

data class NavItem(
    val screen: Screen,
    val label: String,
    val icon: ImageVector
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val items = listOf(
        NavItem(Screen.Home, "Home", Icons.Rounded.Home),
        NavItem(Screen.Families, "Families", Icons.Rounded.People),
        NavItem(Screen.Polisava, "Polisava", Icons.Rounded.Event),
        NavItem(Screen.Updates, "Updates", Icons.AutoMirrored.Rounded.Announcement),
        NavItem(Screen.Settings, "Settings", Icons.Rounded.Settings)
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { 
                    Text(
                        text = "Nuagaon Telisahi",
                        fontWeight = FontWeight.Bold
                    ) 
                },
                navigationIcon = {
                    IconButton(onClick = { /* TODO: Open drawer */ }) {
                        Icon(Icons.Rounded.Menu, contentDescription = "Menu")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                    titleContentColor = MaterialTheme.colorScheme.onSurface,
                )
            )
        },
        bottomBar = {
            NavigationBar(
                tonalElevation = 8.dp,
                containerColor = MaterialTheme.colorScheme.surface
            ) {
                items.forEach { item ->
                    val selected = currentDestination?.hierarchy?.any { it.route == item.screen.route } == true
                    NavigationBarItem(
                        icon = { 
                            Icon(
                                imageVector = item.icon, 
                                contentDescription = item.label
                            ) 
                        },
                        label = { 
                            Text(
                                text = item.label,
                                fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal
                            ) 
                        },
                        selected = selected,
                        onClick = {
                            navController.navigate(item.screen.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            NavHost(
                navController = navController,
                startDestination = Screen.Home.route,
                enterTransition = { fadeIn() + slideInVertically { it / 10 } },
                exitTransition = { fadeOut() }
            ) {
                composable(Screen.Home.route) {
                    DashboardScreen()
                }
                composable(Screen.Families.route) {
                    CommunityScreen()
                }
                composable(Screen.Polisava.route) {
                    MeetingsScreen()
                }
                composable(Screen.Updates.route) {
                    AnnouncementsScreen()
                }
                composable(Screen.Settings.route) {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text("Settings Screen")
                    }
                }
            }
        }
    }
}
