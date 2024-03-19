package com.example.fakepostsapp.presentation.view.user_posts.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fakepostsapp.domain.usecase.GetUserPostsUseCase
import com.example.fakepostsapp.presentation.mapper.postDomainToUi
import com.example.fakepostsapp.presentation.view.user_posts.view.UserPostState
import com.example.fakepostsapp.utilities.Response
import com.example.fakepostsapp.utilities.checkNetworkConnectivity
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class UserPostsViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val getUserPostsUseCase: GetUserPostsUseCase
) :
    ViewModel() {
    private val mutablePostState: MutableStateFlow<UserPostState.Success> =
        MutableStateFlow(UserPostState.Success())
    val postState = mutablePostState.asStateFlow()
    private val mutableErrorState: MutableSharedFlow<UserPostState.Failure> = MutableSharedFlow()
    val errorState = mutableErrorState.asSharedFlow()

    fun getUSerPosts() {
        viewModelScope.launch {
            if (checkNetworkConnectivity(context)) {
                mutablePostState.update {
                    it.copy(loading = true)
                }
                getUserPostsUseCase.getPost().collectLatest { response ->
                    when (response) {
                        is Response.Success -> {
                            response.data?.let {
                                Log.e("UserPostViewModel", "Data: $it")
                                mutablePostState.update { state ->
                                    state.copy(
                                        postUiModel = response.data.map {
                                            it.postDomainToUi()
                                        }, loading = false
                                    )
                                }
                            }
                        }

                        is Response.Failure -> {
                            response.error?.let { errorMessage ->
                                Log.e("USerPostViewModel", "Data: $errorMessage")
                                mutableErrorState.emit(UserPostState.Failure(errorMessage))
                            }
                            mutablePostState.update { it.copy(loading = false) }
                        }
                    }
                }
            } else {
                mutableErrorState.emit(UserPostState.Failure("No Internet Connection"))
            }
        }
    }
}