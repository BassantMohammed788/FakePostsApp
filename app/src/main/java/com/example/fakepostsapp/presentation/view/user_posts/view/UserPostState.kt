package com.example.fakepostsapp.presentation.view.user_posts.view

import com.example.fakepostsapp.presentation.model.PostEntityUi

sealed class UserPostState {
    data class Success(
        var postUiModel: List<PostEntityUi> = listOf(PostEntityUi(body = "", id = 1, title = "", userId = 1)),
        val loading: Boolean = false
    ) : UserPostState()

    data class Failure(val errorMsg: String = "") : UserPostState()
}