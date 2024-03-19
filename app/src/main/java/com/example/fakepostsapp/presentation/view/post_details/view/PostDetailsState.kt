package com.example.fakepostsapp.presentation.view.post_details.view

import com.example.fakepostsapp.presentation.model.PostEntityUi


sealed class PostDetailsState {
    data class Success(
        var postEntityUi: PostEntityUi =
            PostEntityUi(
                body = "",
                id = 1,
                title = "",
                userId = 1
            )
        ,
        val loading: Boolean = false
    ) : PostDetailsState()

    data class Failure(val errorMsg: String = "") : PostDetailsState()
}