package com.nuagaon.telisahi.domain.model

import java.util.Date

data class Meeting(
    val id: String,
    val title: String,
    val description: String,
    val date: Date,
    val location: String,
    val attendeesCount: Int,
    val status: MeetingStatus
)

enum class MeetingStatus {
    UPCOMING, COMPLETED, CANCELLED
}
