package com.nuagaon.telisahi.domain.model

data class VillageMember(
    val id: String,
    val name: String,
    val role: String, // e.g., "Sarpanch", "Resident", "Teacher"
    val area: String, // e.g., "Telisahi", "Main Nuagaon"
    val contact: String
)

data class DevelopmentProject(
    val id: String,
    val title: String,
    val description: String,
    val progress: Float, // 0.0 to 1.0
    val category: ProjectCategory,
    val budget: String
)

enum class ProjectCategory {
    EDUCATION, INFRASTRUCTURE, WATER, HEALTH, SANITATION
}
