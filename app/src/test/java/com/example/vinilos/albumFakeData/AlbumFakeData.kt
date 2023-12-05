package com.example.vinilos.albumFakeData

import com.example.vinilos.data.model.album.Album
import com.example.vinilos.data.network.models.albumNetwork.AlbumNetwork


object AlbumFakeData {

    val albumsNetworkData = listOf(
        AlbumNetwork(
            id = 1,
            name = "Album01",
            cover = "www.album01.com",
            releaseDate = "2021",
            comments = emptyList(),
            tracks = emptyList()

        ),
        AlbumNetwork(
            id = 2,
            name = "Album02",
            cover = "www.album02.com",
            releaseDate = "2022",
            comments = emptyList(),
            tracks = emptyList()
        ),
    )

    val albumsData = listOf(
        Album(
            id = 1,
            name = "Album01",
            cover = "www.album01.com"

        ),
        Album(
            id = 2,
            name = "Album02",
            cover = "www.album02.com"
        ),
    )
}