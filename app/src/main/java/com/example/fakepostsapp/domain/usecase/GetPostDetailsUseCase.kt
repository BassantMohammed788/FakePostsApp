package com.example.fakepostsapp.domain.usecase

import com.example.fakepostsapp.domain.entity.PostEntityDomain
import com.example.fakepostsapp.domain.repository.Repository
import com.example.fakepostsapp.utilities.Response
import kotlinx.coroutines.flow.Flow

import javax.inject.Inject

class GetPostDetailsUseCase @Inject constructor(private val repository: Repository) {
    suspend fun getPostDetailsById(id: String): Flow<Response<PostEntityDomain>> {
        return repository.getPostByUserId(id)
    }
}