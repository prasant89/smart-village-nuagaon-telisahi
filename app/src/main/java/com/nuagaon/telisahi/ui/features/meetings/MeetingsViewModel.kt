package com.nuagaon.telisahi.ui.features.meetings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nuagaon.telisahi.domain.model.Meeting
import com.nuagaon.telisahi.domain.model.MeetingStatus
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
class MeetingsViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(MeetingState())
    val uiState: StateFlow<MeetingState> = _uiState.asStateFlow()

    init {
        loadMeetings()
    }

    fun onEvent(event: MeetingEvent) {
        when (event) {
            is MeetingEvent.LoadMeetings -> loadMeetings()
            is MeetingEvent.OnMeetingClick -> { /* Handle click */ }
            is MeetingEvent.OnAddMeetingClick -> { /* Handle add */ }
        }
    }

    private fun loadMeetings() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            
            // Simulate network delay
            delay(1500)
            
            val dummyMeetings = listOf(
                Meeting(
                    id = "1",
                    title = "Monthly Village Council",
                    description = "Discussion on water management and drainage system improvement.",
                    date = Date(),
                    location = "Village Community Hall",
                    attendeesCount = 45,
                    status = MeetingStatus.UPCOMING
                ),
                Meeting(
                    id = "2",
                    title = "School Development Committee",
                    description = "Planning for the new library wing and teacher appointments.",
                    date = Date(System.currentTimeMillis() - 86400000 * 2),
                    location = "Nuagaon Primary School",
                    attendeesCount = 12,
                    status = MeetingStatus.COMPLETED
                ),
                Meeting(
                    id = "3",
                    title = "Health Awareness Camp",
                    description = "Planning for upcoming vaccination drive in Telisahi area.",
                    date = Date(System.currentTimeMillis() + 86400000 * 5),
                    location = "Telisahi Sub-center",
                    attendeesCount = 0,
                    status = MeetingStatus.UPCOMING
                )
            )
            
            _uiState.update { 
                it.copy(
                    isLoading = false,
                    meetings = dummyMeetings
                ) 
            }
        }
    }
}
