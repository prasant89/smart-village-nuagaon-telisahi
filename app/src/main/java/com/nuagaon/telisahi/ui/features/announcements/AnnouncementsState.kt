package com.nuagaon.telisahi.ui.features.announcements

import com.nuagaon.telisahi.domain.model.Announcement

data class AnnouncementsState(
    val isLoading: Boolean = false,
    val announcements: List<Announcement> = emptyList(),
    val error: String? = null
)

sealed class AnnouncementsEvent {
    object LoadAnnouncements : AnnouncementsEvent()
    data class OnAnnouncementClick(val announcementId: String) : AnnouncementsEvent()
}
