package com.nuagaon.telisahi.ui.features.meetings

import com.nuagaon.telisahi.domain.model.Meeting

data class MeetingState(
    val isLoading: Boolean = false,
    val meetings: List<Meeting> = emptyList(),
    val error: String? = null
)

sealed class MeetingEvent {
    object LoadMeetings : MeetingEvent()
    data class OnMeetingClick(val meetingId: String) : MeetingEvent()
    object OnAddMeetingClick : MeetingEvent()
}
