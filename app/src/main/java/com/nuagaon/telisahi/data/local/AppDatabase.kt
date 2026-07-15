package com.nuagaon.telisahi.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.nuagaon.telisahi.data.local.dao.VillageDao
import com.nuagaon.telisahi.data.local.entities.*

@Database(
    entities = [
        Village::class,
        House::class,
        Family::class,
        Member::class,
        Attendance::class,
        Polisava::class,
        Announcement::class,
        ImageEntity::class,
        VideoEntity::class,
        School::class,
        VillageEvent::class,
        EmergencyContact::class,
        DevelopmentProject::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun villageDao(): VillageDao
}
