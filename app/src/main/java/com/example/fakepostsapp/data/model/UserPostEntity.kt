package com.example.fakepostsapp.data.model

import java.io.Serializable


typealias PostsEntity = List<UserPostEntity>

data class UserPostEntity(
    val id: Int? = null,
    val title: String? = null,
    val body: String? = null,
    val userId: Int? = null
):Serializable