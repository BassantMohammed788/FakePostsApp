package com.example.fakepostsapp.presntation.view.user_posts.viewmodel

import com.example.fakepostsapp.domain.usecase.GetUserPostsUseCase
import javax.inject.Inject


class UserPostsViewModel @Inject constructor(private val getUserPostsUseCase:GetUserPostsUseCase) {

}