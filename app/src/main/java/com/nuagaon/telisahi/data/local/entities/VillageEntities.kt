package com.nuagaon.telisahi.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "villages")
data class Village(
    @PrimaryKey val id: String,
    val name: String,
    val block: String,
    val district: String,
    val state: String,
    val pincode: String
)

@Entity(tableName = "houses")
data class House(
    @PrimaryKey val houseId: String,
    val houseNumber: String,
    val wardNumber: Int,
    val villageId: String
)

@Entity(tableName = "families")
data class Family(
    @PrimaryKey val familyId: String,
    val headOfFamilyName: String,
    val houseId: String,
    val contactNumber: String
)

@Entity(tableName = "members")
data class Member(
    @PrimaryKey val memberId: String,
    val name: String,
    val age: Int,
    val gender: String,
    val familyId: String,
    val occupation: String,
    val qualification: String,
    val bloodGroup: String?,
    val phoneNumber: String?
)

@Entity(tableName = "attendance")
data class Attendance(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val meetingId: String,
    val memberId: String,
    val status: String, // Present, Absent
    val date: Long
)

@Entity(tableName = "polisava")
data class Polisava(
    @PrimaryKey val id: String,
    val title: String,
    val description: String,
    val dateTime: Long,
    val venue: String,
    val organizer: String
)

@Entity(tableName = "announcements")
data class Announcement(
    @PrimaryKey val id: String,
    val title: String,
    val content: String,
    val postedDate: Long,
    val priority: String // High, Medium, Low
)

@Entity(tableName = "images")
data class ImageEntity(
    @PrimaryKey val id: String,
    val url: String,
    val description: String?,
    val category: String, // Event, Infrastructure, etc.
    val uploadedDate: Long
)

@Entity(tableName = "videos")
data class VideoEntity(
    @PrimaryKey val id: String,
    val url: String,
    val title: String,
    val thumbnail: String?,
    val uploadedDate: Long
)

@Entity(tableName = "schools")
data class School(
    @PrimaryKey val id: String,
    val name: String,
    val type: String, // Primary, Secondary, etc.
    val principalName: String,
    val totalStudents: Int,
    val totalTeachers: Int
)

@Entity(tableName = "village_events")
data class VillageEvent(
    @PrimaryKey val id: String,
    val title: String,
    val description: String,
    val eventDate: Long,
    val venue: String
)

@Entity(tableName = "emergency_contacts")
data class EmergencyContact(
    @PrimaryKey val id: String,
    val name: String,
    val designation: String, // Sarpanch, Ward Member, Police, Hospital
    val phoneNumber: String
)

@Entity(tableName = "development_projects")
data class DevelopmentProject(
    @PrimaryKey val id: String,
    val name: String,
    val description: String,
    val status: String, // Ongoing, Completed, Proposed
    val budget: Double,
    val startDate: Long,
    val endDate: Long?
)
