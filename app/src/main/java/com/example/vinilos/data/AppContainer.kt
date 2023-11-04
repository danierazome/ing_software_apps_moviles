package com.example.vinilos.data

import com.example.vinilos.network.AlbumApiService
import com.example.vinilos.network.CollectorApiService
import com.example.vinilos.network.MusicianApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer {
    val albumRepository: AlbumRepository
    val musicianRepository: MusicianRepository
    val collectorRepository: CollectorRepository

}

class DefaultAppContainer: AppContainer {

    private val baseUrl = "http://vynils-back-heroku.herokuapp.com"

    private  val contentType = "application/json".toMediaType()
    private  val jsonConverter = Json {
        ignoreUnknownKeys = true
    }.asConverterFactory(contentType)

    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(jsonConverter)
        .baseUrl(baseUrl)
        .build()

    // ALBUM
    private val retrofitAlbumService: AlbumApiService by lazy {
        retrofit.create(AlbumApiService::class.java)
    }

    override val albumRepository: AlbumRepository by lazy {
        NetworkAlbumRepository(retrofitAlbumService)
    }

    // MUSICIAN
    private val retrofitMusicianService: MusicianApiService by lazy {
        retrofit.create(MusicianApiService::class.java)
    }

    override val musicianRepository: MusicianRepository by lazy {
        NetworkMusicianRepository(retrofitMusicianService)
    }

    // COLLECTOR

    private val retrofitCollectorService: CollectorApiService by lazy {
        retrofit.create(CollectorApiService::class.java)
    }

    override val collectorRepository: CollectorRepository by lazy {
        NetworkCollectorRepository(retrofitCollectorService)
    }
}