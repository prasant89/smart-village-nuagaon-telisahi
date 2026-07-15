package com.nuagaon.telisahi.domain.model

import java.util.Date

data class Announcement(
    val id: String,
    val title: String,
    val content: String,
    val date: Date,
    val category: AnnouncementCategory,
    val priority: AnnouncementPriority = AnnouncementPriority.NORMAL
)

enum class AnnouncementCategory {
    GENERAL, EMERGENCY, HEALTH, EDUCATION, DEVELOPMENT
}

enum class AnnouncementPriority {
    LOW, NORMAL, HIGH, URGENT
}
