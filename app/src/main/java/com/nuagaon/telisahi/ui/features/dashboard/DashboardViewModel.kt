package com.nuagaon.telisahi.ui.features.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nuagaon.telisahi.data.local.entities.*
import com.nuagaon.telisahi.data.repository.VillageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val repository: VillageRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(DashboardState())
    val uiState: StateFlow<DashboardState> = _uiState.asStateFlow()

    init {
        observeDashboardData()
    }

    private fun observeDashboardData() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            
            val houseCountFlow = repository.getHouseCount()
            val familyCountFlow = repository.getFamilyCount()
            val memberCountFlow = repository.getMemberCount()
            val polisavaFlow = repository.getUpcomingPolisava()
            val announcementsFlow = repository.getRecentAnnouncements()
            val schoolsFlow = repository.getSchools()
            val imagesFlow = repository.getGalleryImages()
            val videosFlow = repository.getVideos()

            combine(
                houseCountFlow,
                familyCountFlow,
                memberCountFlow,
                polisavaFlow,
                announcementsFlow,
                schoolsFlow,
                imagesFlow,
                videosFlow
            ) { args: Array<*> ->
                DashboardState(
                    isLoading = false,
                    houseCount = args[0] as Int,
                    familyCount = args[1] as Int,
                    memberCount = args[2] as Int,
                    upcomingPolisava = args[3] as? Polisava,
                    recentAnnouncements = args[4] as List<Announcement>,
                    schoolCount = (args[5] as List<School>).size,
                    imageCount = (args[6] as List<ImageEntity>).size,
                    videoCount = (args[7] as List<VideoEntity>).size
                )
            }.collect { newState ->
                _uiState.value = newState
            }
        }
    }

    fun onEvent(event: DashboardEvent) {
        when (event) {
            is DashboardEvent.Refresh -> observeDashboardData()
        }
    }
}
