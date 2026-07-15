package com.nuagaon.telisahi.ui.features.dashboard

import com.nuagaon.telisahi.data.local.entities.Announcement
import com.nuagaon.telisahi.data.local.entities.Polisava

data class DashboardState(
    val villageName: String = "Nuagaon",
    val area: String = "Telisahi",
    val isLoading: Boolean = false,
    val houseCount: Int = 0,
    val familyCount: Int = 0,
    val memberCount: Int = 0,
    val upcomingPolisava: Polisava? = null,
    val recentAnnouncements: List<Announcement> = emptyList(),
    val schoolCount: Int = 0,
    val imageCount: Int = 0,
    val videoCount: Int = 0
)

sealed class DashboardEvent {
    object Refresh : DashboardEvent()
}
