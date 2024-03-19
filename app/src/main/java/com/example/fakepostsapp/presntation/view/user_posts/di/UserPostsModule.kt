package com.example.fakepostsapp.presntation.view.user_posts.di

import com.example.fakepostsapp.domain.repository.Repository
import com.example.fakepostsapp.domain.usecase.GetUserPostsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped


@Module
@InstallIn(ViewModelComponent::class)
object UserPostsModule {
    @Provides
    @ViewModelScoped
    fun provideUserPostsUseCase(repository: Repository): GetUserPostsUseCase {
        return GetUserPostsUseCase(repository)
    }
}