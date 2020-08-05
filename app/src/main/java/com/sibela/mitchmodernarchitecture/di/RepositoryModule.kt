package com.sibela.mitchmodernarchitecture.di

import com.sibela.mitchmodernarchitecture.repository.MainRepository
import com.sibela.mitchmodernarchitecture.retrofit.BlogRetrofit
import com.sibela.mitchmodernarchitecture.retrofit.NetworkMapper
import com.sibela.mitchmodernarchitecture.room.BlogDao
import com.sibela.mitchmodernarchitecture.room.CacheMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideMainRepository(
        blogDao: BlogDao,
        blogRetrofit: BlogRetrofit,
        cacheMapper: CacheMapper,
        networkMapper: NetworkMapper
    ): MainRepository {
        return MainRepository(blogDao, blogRetrofit, cacheMapper, networkMapper)
    }

}