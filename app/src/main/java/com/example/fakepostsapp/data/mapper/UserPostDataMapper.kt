package com.example.fakepostsapp.data.mapper

import com.example.fakepostsapp.data.model.PostsEntity
import com.example.fakepostsapp.data.model.UserPostEntity
import com.example.fakepostsapp.domain.entity.PostEntityDomain


fun UserPostEntity.toPostEntityDomain(): PostEntityDomain =
    PostEntityDomain(
        body = body, id = id, title = title, userId = userId
    )

fun PostsEntity.toPostEntityDomain(): List<PostEntityDomain> = this.map{ it.toPostEntityDomain()  }