package com.example.cricradio2.di

import com.example.cricradio.repository.ApiService
import com.example.cricradio.repository.Repository
import com.example.cricradio.repository.RepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.header
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApi(): HttpClient {
        return HttpClient(Android){
            install(
                ContentNegotiation
            ){
                json(Json{
                    ignoreUnknownKeys = true
                    coerceInputValues = true})
            }
            defaultRequest {
                header("Authorization", "Basic Y3JpY2tldFJhZGlvOmNyaWNrZXRAJCUjUmFkaW8xMjM=")
            }
        }
    }

    @Provides
    @Singleton
    fun provideApiService(client: HttpClient): ApiService {
        return RepositoryImpl(client)
    }

    @Provides
    @Singleton
    fun provideRepository(apiService: ApiService): Repository {
        return Repository(apiService)
    }
}