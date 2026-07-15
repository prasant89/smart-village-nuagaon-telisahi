package com.nuagaon.telisahi.di

import android.content.Context
import androidx.room.Room
import com.nuagaon.telisahi.data.local.AppDatabase
import com.nuagaon.telisahi.data.local.dao.VillageDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "nuagaon_database"
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    fun provideVillageDao(database: AppDatabase): VillageDao {
        return database.villageDao()
    }
}
