package com.example.vinilos.data.mappers

import com.example.vinilos.data.local.entities.musician.MusicianEntity
import com.example.vinilos.data.model.album.Album
import com.example.vinilos.data.network.models.network.ArtistNetwork
import com.example.vinilos.data.model.musician.DetailedArtist
import com.example.vinilos.data.network.models.network.TrackNetwork


fun ArtistNetwork.asEntity() = MusicianEntity(
    id = id,
    name = name,
    image = image,
    description = description,
    birthDate = birthDate
)

/*fun ArtistNetwork.asUIDetailedModel() = DetailedArtist(
    id = id,
    name = name,
    image = image,
    description = description,
    birthDate = birthDate,
    albums: albums,
)*/
