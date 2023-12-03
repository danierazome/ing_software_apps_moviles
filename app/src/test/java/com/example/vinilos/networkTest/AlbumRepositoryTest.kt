package com.example.vinilos.networkTest

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.example.vinilos.albumFakeData.AlbumFakeData
import com.example.vinilos.albumFakeData.FakeAlbumApiService
import com.example.vinilos.data.local.AppDatabase
import com.example.vinilos.data.network.dataSources.AlbumRemoteDataSource
import com.example.vinilos.data.repository.AlbumRepository
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

//@RunWith(AndroidJUnit4::class)
//@SmallTest
//class AlbumRepositoryTest {
//
//    private lateinit var db: AppDatabase
//
//    @Before
//    fun createDb() {
//        val context = ApplicationProvider.getApplicationContext<Context>()
//        db = Room.inMemoryDatabaseBuilder(
//            context, AppDatabase::class.java).build()
//    }
//
//    @After
//    @Throws(IOException::class)
//    fun closeDb() {
//        db.close()
//    }
//
//    @Test
//    fun networkAlbumRepository_getAlbums() =
//        runTest {
//            val repository = AlbumRepository(
//                albumRemoteDataSource = AlbumRemoteDataSource(
//                    albumApiService = FakeAlbumApiService()
//                ),
//                albumDao = db.albumDao(),
//                trackDao = db.trackDao(),
//                commentDao = db.commentDao()
//            )
//            assertEquals(AlbumFakeData.albumsData, repository.getAlbums())
//        }
//}