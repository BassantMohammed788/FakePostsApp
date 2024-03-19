package com.example.fakepostsapp.data.repository

import com.example.fakepostsapp.data.mapper.toPostEntityDomain
import com.example.fakepostsapp.data.remote.RemoteSource
import com.example.fakepostsapp.domain.entity.PostEntityDomain
import com.example.fakepostsapp.domain.repository.Repository
import com.example.fakepostsapp.utilities.Response
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val remoteSource: RemoteSource) :
    Repository {
    override suspend fun getUserPosts(): Flow<Response<List<PostEntityDomain>>> {
        return try{
            remoteSource.getUserPosts().map {
                Response.Success(it.toPostEntityDomain())
            }
        } catch (e: Exception) {
            flowOf(Response.Failure(e.message ?: "Unknown Exception"))
        }
    }

    override suspend fun getPostByUserId(id: String): Flow<Response<PostEntityDomain>> {
        return try{
            remoteSource.getPostDetailsById(id).map {
                Response.Success(it.toPostEntityDomain())
            }
        } catch (e: Exception) {
            flowOf(Response.Failure(e.message ?: "Unknown Exception"))
        }
    }
}