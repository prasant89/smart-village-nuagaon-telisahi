package com.nuagaon.telisahi.data.local.dao

import androidx.room.*
import com.nuagaon.telisahi.data.local.entities.*
import kotlinx.coroutines.flow.Flow

@Dao
interface VillageDao {
    @Query("SELECT COUNT(*) FROM houses")
    fun getHouseCount(): Flow<Int>

    @Query("SELECT COUNT(*) FROM families")
    fun getFamilyCount(): Flow<Int>

    @Query("SELECT COUNT(*) FROM members")
    fun getMemberCount(): Flow<Int>

    @Query("SELECT * FROM polisava ORDER BY dateTime DESC LIMIT 1")
    fun getUpcomingPolisava(): Flow<Polisava?>

    @Query("SELECT * FROM announcements ORDER BY postedDate DESC LIMIT 5")
    fun getRecentAnnouncements(): Flow<List<Announcement>>

    @Query("SELECT * FROM schools")
    fun getSchools(): Flow<List<School>>

    @Query("SELECT * FROM images ORDER BY uploadedDate DESC")
    fun getGalleryImages(): Flow<List<ImageEntity>>

    @Query("SELECT * FROM videos ORDER BY uploadedDate DESC")
    fun getVideos(): Flow<List<VideoEntity>>

    @Query("SELECT * FROM development_projects")
    fun getDevelopmentProjects(): Flow<List<DevelopmentProject>>

    @Query("SELECT * FROM emergency_contacts")
    fun getEmergencyContacts(): Flow<List<EmergencyContact>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVillage(village: Village)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHouse(house: House)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFamily(family: Family)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMember(member: Member)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPolisava(polisava: Polisava)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAnnouncement(announcement: Announcement)
}
