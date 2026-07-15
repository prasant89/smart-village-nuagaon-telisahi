package com.nuagaon.telisahi.data.repository

import com.nuagaon.telisahi.data.local.dao.VillageDao
import com.nuagaon.telisahi.data.local.entities.*
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class VillageRepository @Inject constructor(
    private val villageDao: VillageDao
) {
    fun getHouseCount(): Flow<Int> = villageDao.getHouseCount()
    fun getFamilyCount(): Flow<Int> = villageDao.getFamilyCount()
    fun getMemberCount(): Flow<Int> = villageDao.getMemberCount()
    fun getUpcomingPolisava(): Flow<Polisava?> = villageDao.getUpcomingPolisava()
    fun getRecentAnnouncements(): Flow<List<Announcement>> = villageDao.getRecentAnnouncements()
    fun getSchools(): Flow<List<School>> = villageDao.getSchools()
    fun getGalleryImages(): Flow<List<ImageEntity>> = villageDao.getGalleryImages()
    fun getVideos(): Flow<List<VideoEntity>> = villageDao.getVideos()
    fun getDevelopmentProjects(): Flow<List<DevelopmentProject>> = villageDao.getDevelopmentProjects()
    fun getEmergencyContacts(): Flow<List<EmergencyContact>> = villageDao.getEmergencyContacts()

    suspend fun insertVillage(village: Village) = villageDao.insertVillage(village)
    suspend fun insertHouse(house: House) = villageDao.insertHouse(house)
    suspend fun insertFamily(family: Family) = villageDao.insertFamily(family)
    suspend fun insertMember(member: Member) = villageDao.insertMember(member)
    suspend fun insertPolisava(polisava: Polisava) = villageDao.insertPolisava(polisava)
    suspend fun insertAnnouncement(announcement: Announcement) = villageDao.insertAnnouncement(announcement)
}
