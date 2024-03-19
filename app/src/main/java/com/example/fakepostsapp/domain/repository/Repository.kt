package com.example.fakepostsapp.domain.repository

import com.example.fakepostsapp.domain.entity.PostEntityDomain
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface Repository {
    suspend fun getUserPosts(): Flow<Response<List<PostEntityDomain>>>
    suspend fun getPostByUserId(id:String): Flow<Response<PostEntityDomain>>
}