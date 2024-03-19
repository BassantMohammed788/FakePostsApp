package com.example.fakepostsapp.di

import com.example.fakepostsapp.data.remote.ApiClient
import com.example.fakepostsapp.data.remote.ApiService
import com.example.fakepostsapp.data.remote.RemoteSource
import com.example.fakepostsapp.data.repository.RepositoryImpl
import com.example.fakepostsapp.domain.repository.Repository
import com.example.fakepostsapp.utilities.Constants
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {
    companion object {
        @Provides
        @Singleton
        fun providesLoggingInterceptor(): HttpLoggingInterceptor {
            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BASIC)
            return logging
        }

        @Provides
        @Singleton
        fun providesOkHttpClient(okHttpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
            return OkHttpClient.Builder()
                .addInterceptor(okHttpLoggingInterceptor)
                .build()
        }

        @Provides
        @Singleton
        fun provideUrl(): String = Constants.BASE_URL

        @Provides
        @Singleton
        fun provideApi(okHttpClient: OkHttpClient, baseUrl: String): ApiService {
            return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create()).client(okHttpClient)
                .addConverterFactory(
                    GsonConverterFactory.create()
                )
                .baseUrl(baseUrl)
                .build().create(ApiService::class.java)
        }
    }

    @Binds
    @Singleton
    abstract fun provideRemoteSource(apiClient: ApiClient): RemoteSource

    @Binds
    @Singleton
    abstract fun provideRepository(repository: RepositoryImpl): Repository
}