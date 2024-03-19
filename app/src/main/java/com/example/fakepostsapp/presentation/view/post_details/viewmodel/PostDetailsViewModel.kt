package com.example.fakepostsapp.presentation.view.post_details.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fakepostsapp.domain.usecase.GetPostDetailsUseCase
import com.example.fakepostsapp.presentation.mapper.postDomainToUi
import com.example.fakepostsapp.presentation.view.post_details.view.PostDetailsState
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
class PostDetailsViewModel @Inject constructor(
    private val getPostDetailsUseCase: GetPostDetailsUseCase
) :
    ViewModel() {
    private val mutablePostDetailsState: MutableStateFlow<PostDetailsState.Success> =
        MutableStateFlow(PostDetailsState.Success())
    val postDetailsState = mutablePostDetailsState.asStateFlow()
    private val mutableErrorState: MutableSharedFlow<PostDetailsState.Failure> = MutableSharedFlow()
    val errorState = mutableErrorState.asSharedFlow()

    fun getPostDetailsById(id: String) {


        mutablePostDetailsState.update {
            it.copy(loading = true)
        }
        viewModelScope.launch {
            getPostDetailsUseCase.getPostDetailsById(id).collectLatest { response ->
                when (response) {
                    is Response.Success -> {
                        response.data?.let {
                            mutablePostDetailsState.update { state ->
                                state.copy(
                                    postEntityUi = response.data.postDomainToUi(),
                                    loading = false
                                )
                            }
                        }

                    }

                    is Response.Failure -> {
                        response.error?.let { errorMessage ->
                            mutableErrorState.emit(PostDetailsState.Failure(errorMessage))
                        }
                        mutablePostDetailsState.update { it.copy(loading = false) }
                    }
                }
            }
        }
    }
}

