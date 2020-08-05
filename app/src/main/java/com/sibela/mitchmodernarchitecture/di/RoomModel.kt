package com.sibela.mitchmodernarchitecture.di

import android.content.Context
import androidx.room.Room
import com.google.gson.Gson
import com.sibela.mitchmodernarchitecture.retrofit.BlogRetrofit
import com.sibela.mitchmodernarchitecture.room.BlogDao
import com.sibela.mitchmodernarchitecture.room.BlogDatabase
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
object RoomModel {

    @Singleton
    @Provides
    fun provideBlogDb(@ApplicationContext context: Context): BlogDatabase {
        return Room.databaseBuilder(
            context,
            BlogDatabase::class.java,
            BlogDatabase.DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideBlogDAO(blogDatabase: BlogDatabase) = blogDatabase.blogDao()
}