package com.nuagaon.telisahi.ui.features.announcements

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nuagaon.telisahi.domain.model.Announcement
import com.nuagaon.telisahi.domain.model.AnnouncementCategory
import com.nuagaon.telisahi.domain.model.AnnouncementPriority
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class AnnouncementsViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(AnnouncementsState())
    val uiState: StateFlow<AnnouncementsState> = _uiState.asStateFlow()

    init {
        loadAnnouncements()
    }

    fun onEvent(event: AnnouncementsEvent) {
        when (event) {
            is AnnouncementsEvent.LoadAnnouncements -> loadAnnouncements()
            is AnnouncementsEvent.OnAnnouncementClick -> { /* Handle click */ }
        }
    }

    private fun loadAnnouncements() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            
            // Simulate network delay
            delay(1200)
            
            val dummyAnnouncements = listOf(
                Announcement(
                    id = "1",
                    title = "Urgent: Water Supply Maintenance",
                    content = "Water supply will be interrupted tomorrow from 10 AM to 4 PM for pipeline repairs in Telisahi area.",
                    date = Date(),
                    category = AnnouncementCategory.EMERGENCY,
                    priority = AnnouncementPriority.URGENT
                ),
                Announcement(
                    id = "2",
                    title = "Free Vaccination Drive",
                    content = "A free health camp for children's vaccination will be held at the Primary Health Center this Sunday.",
                    date = Date(System.currentTimeMillis() - 86400000),
                    category = AnnouncementCategory.HEALTH,
                    priority = AnnouncementPriority.HIGH
                ),
                Announcement(
                    id = "3",
                    title = "Village Library Expansion",
                    content = "We are happy to announce that 500 new books have been added to the village library. Visit today!",
                    date = Date(System.currentTimeMillis() - 86400000 * 3),
                    category = AnnouncementCategory.EDUCATION,
                    priority = AnnouncementPriority.NORMAL
                )
            )
            
            _uiState.update { 
                it.copy(
                    isLoading = false,
                    announcements = dummyAnnouncements
                ) 
            }
        }
    }
}
