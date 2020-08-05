package com.sibela.mitchmodernarchitecture.repository

import com.sibela.mitchmodernarchitecture.model.Blog
import com.sibela.mitchmodernarchitecture.retrofit.BlogRetrofit
import com.sibela.mitchmodernarchitecture.retrofit.NetworkMapper
import com.sibela.mitchmodernarchitecture.room.BlogDao
import com.sibela.mitchmodernarchitecture.room.CacheMapper
import com.sibela.mitchmodernarchitecture.util.DataState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MainRepository constructor(
    private val blogDao: BlogDao,
    private val blogRetrofit: BlogRetrofit,
    private val cacheMapper: CacheMapper,
    private val networkMapper: NetworkMapper
) {

    suspend fun getBlogs(): Flow<DataState<List<Blog>>> = flow {
        emit(DataState.Loading)
        delay(1000) // just used to see the loading
        try {
            val networkBlogs = blogRetrofit.getAll()
            val blogs = networkMapper.mapFromEntityList(networkBlogs)
            blogs.forEach { blog -> blogDao.insert(cacheMapper.mapToEntity(blog)) }
            val cacheBlogs = blogDao.getAll()
            emit(DataState.Success(cacheMapper.mapFromEntityList(cacheBlogs)))
        } catch (exception: Exception) {
            emit(DataState.Error(exception))

        }
    }

}