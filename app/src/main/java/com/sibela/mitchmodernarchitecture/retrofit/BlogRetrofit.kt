package com.sibela.mitchmodernarchitecture.retrofit

import retrofit2.http.GET

interface BlogRetrofit {

    @GET("blogs")
    suspend fun getAll(): List<BlogNetworkEntity>

}