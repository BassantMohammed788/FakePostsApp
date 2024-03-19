package com.example.fakepostsapp.data.remote

import com.example.fakepostsapp.data.model.PostsEntity
import com.example.fakepostsapp.data.model.UserPostEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class ApiClient @Inject constructor(private val apiService: ApiService) : RemoteSource {
    override suspend fun getPostDetailsById(id: String): Flow<UserPostEntity> {
        return flowOf(apiService.getPostDetailsById(id))
    }

    override suspend fun getUserPosts(): Flow<PostsEntity> {
        return flowOf(apiService.getUserPosts())
    }

}