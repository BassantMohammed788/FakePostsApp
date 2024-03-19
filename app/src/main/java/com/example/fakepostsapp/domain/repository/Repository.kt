package com.example.fakepostsapp.domain.repository

import com.example.fakepostsapp.domain.entity.PostEntityDomain
import com.example.fakepostsapp.utilities.Response
import kotlinx.coroutines.flow.Flow


interface Repository {
    suspend fun getUserPosts(): Flow<Response<List<PostEntityDomain>>>
    suspend fun getPostByUserId(id:String): Flow<Response<PostEntityDomain>>
}