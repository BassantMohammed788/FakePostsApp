package com.example.fakepostsapp.data.remote

import com.example.fakepostsapp.data.model.PostsEntity
import com.example.fakepostsapp.data.model.UserPostEntity
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("/posts")
    suspend fun getUserPosts(): PostsEntity

    @GET("/posts/{id}")
    suspend fun getPostDetailsById(@Path("id") id: String): UserPostEntity
}