package com.example.fakepostsapp.domain.usecase

import com.example.fakepostsapp.domain.entity.PostEntityDomain
import com.example.fakepostsapp.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

class GetUserPostsUseCase @Inject constructor(private val repository: Repository) {
    suspend fun getPost(): Flow<Response<List<PostEntityDomain>>> {
        return repository.getUserPosts()
    }
}