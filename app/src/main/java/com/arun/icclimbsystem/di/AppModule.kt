package com.arun.icclimbsystem.di

import android.content.Context
import androidx.room.Room
import com.arun.icclimbsystem.db.NoteDao
import com.arun.icclimbsystem.db.NoteDatabase
import com.arun.icclimbsystem.other.Constants.BASE_URL
import com.arun.icclimbsystem.other.Constants.DATABASE_NAME
import com.arun.icclimbsystem.remote.Api
import com.arun.icclimbsystem.repositories.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class AppModule {
    @Singleton
    @Provides
    fun provideNoteDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(context, NoteDatabase::class.java, DATABASE_NAME).build()

    @Singleton
    @Provides
    fun provideNoteDao(
        database: NoteDatabase
    ) = database.getNoteDao()

    @Singleton
    @Provides
    fun provideMainRepository(
        dao: NoteDao,
        api:Api
    ) = MainRepository(dao,api)

    @Singleton
    @Provides
    fun provideFacultyApi(): Api {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(Api::class.java)
    }

}