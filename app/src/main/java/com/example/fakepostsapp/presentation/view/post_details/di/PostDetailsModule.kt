package com.example.fakepostsapp.presentation.view.post_details.di

import com.example.fakepostsapp.domain.repository.Repository
import com.example.fakepostsapp.domain.usecase.GetPostDetailsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped


@Module
@InstallIn(ViewModelComponent::class)
object PostDetailsModule {
    @Provides
    @ViewModelScoped
    fun providePostDetailsUseCase(repository: Repository): GetPostDetailsUseCase {
        return GetPostDetailsUseCase(repository)
    }
}