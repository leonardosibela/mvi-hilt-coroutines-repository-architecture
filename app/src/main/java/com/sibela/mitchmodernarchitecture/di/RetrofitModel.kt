package com.sibela.mitchmodernarchitecture.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.sibela.mitchmodernarchitecture.retrofit.BlogRetrofit
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RetrofitModel {

    private const val BASE_URL = "https://open-api.xyz/placeholder/"

    @Singleton
    @Provides
    fun provideGsonBuilder(): Gson {
        return GsonBuilder()
            .excludeFieldsWithModifiers()
            .create()
    }

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Singleton
    @Provides
    fun provideBlogService(retrofit: Retrofit): BlogRetrofit {
        return retrofit
            .create(BlogRetrofit::class.java)
    }
}