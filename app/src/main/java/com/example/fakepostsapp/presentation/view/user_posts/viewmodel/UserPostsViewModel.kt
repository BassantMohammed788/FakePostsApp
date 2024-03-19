package com.example.fakepostsapp.presentation.view.user_posts.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fakepostsapp.domain.usecase.GetUserPostsUseCase
import com.example.fakepostsapp.presentation.view.user_posts.view.UserPostState
import com.example.fakepostsapp.utilities.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class UserPostsViewModel @Inject constructor(private val getUserPostsUseCase:GetUserPostsUseCase):ViewModel() {

}