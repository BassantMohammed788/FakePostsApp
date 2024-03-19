package com.example.fakepostsapp.data.remote

import com.example.fakepostsapp.data.model.PostsEntity
import com.example.fakepostsapp.data.model.UserPostEntity
import kotlinx.coroutines.flow.Flow

interface RemoteSource {
    suspend fun getUserPosts(): Flow<PostsEntity>
    suspend fun getPostDetailsById(id:String): Flow<UserPostEntity>
}