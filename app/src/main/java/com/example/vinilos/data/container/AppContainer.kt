package com.example.vinilos.data.container

import android.content.Context
import com.example.vinilos.data.local.AppDatabase
import com.example.vinilos.data.repository.BandRepository
import com.example.vinilos.data.repository.CollectorRepository
import com.example.vinilos.data.repository.AlbumRepository
import com.example.vinilos.data.repository.NetworkBandRepository
import com.example.vinilos.data.repository.NetworkCollectorRepository
import com.example.vinilos.data.network.apiServices.AlbumApiService
import com.example.vinilos.data.network.apiServices.BandApiService
import com.example.vinilos.data.network.apiServices.CollectorApiService
import com.example.vinilos.data.network.apiServices.MusicianApiService
import com.example.vinilos.data.network.dataSources.AlbumRemoteDataSource
import com.example.vinilos.data.repository.IMusicianRepository
import com.example.vinilos.data.repository.NetworkMusicianRepository
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer {
    val albumRepository: AlbumRepository
    val musicianRepository: IMusicianRepository
    val collectorRepository: CollectorRepository
    val bandRepository: BandRepository
}

class DefaultAppContainer(private val context: Context): AppContainer {

    // ---> REMOTE SOURCE CONFIGURATION
    private val baseUrl = "http://vynils-back-heroku.herokuapp.com"

    private  val contentType = "application/json".toMediaType()
    private  val jsonConverter = Json {
        ignoreUnknownKeys = true
    }.asConverterFactory(contentType)

    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(jsonConverter)
        .baseUrl(baseUrl)
        .build()

    // ---> LOCAL SOURCE CONFIGURATION
    private val database: AppDatabase by lazy { AppDatabase.getDatabase(context) }

    // ALBUM
    private val retrofitAlbumService: AlbumApiService by lazy {
        retrofit.create(AlbumApiService::class.java)
    }

    private val albumRemoteDataSource: AlbumRemoteDataSource by lazy {
        AlbumRemoteDataSource(retrofitAlbumService)
    }

    override val albumRepository: AlbumRepository by lazy {
        AlbumRepository(albumRemoteDataSource, database.albumDao(), database.trackDao(), database.commentDao())
    }
    override val musicianRepository: IMusicianRepository by lazy {
        NetworkMusicianRepository(retrofitMusicianService)
    }


    // MUSICIAN
    private val retrofitMusicianService: MusicianApiService by lazy {
        retrofit.create(MusicianApiService::class.java)
    }

    // COLLECTOR

    private val retrofitCollectorService: CollectorApiService by lazy {
        retrofit.create(CollectorApiService::class.java)
    }

    override val collectorRepository: CollectorRepository by lazy {
        NetworkCollectorRepository(retrofitCollectorService)
    }

    //ALBUM

    private val retrofitBandService: BandApiService by lazy {
        retrofit.create(BandApiService::class.java)
    }

    override val bandRepository: BandRepository by lazy {
        NetworkBandRepository(retrofitBandService)
    }

}
