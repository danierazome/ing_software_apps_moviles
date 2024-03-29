package com.example.vinilos.data.mappers

import com.example.vinilos.data.local.entities.album.AlbumEntity
import com.example.vinilos.data.local.entities.album.AlbumWithTrackAndComment
import com.example.vinilos.data.local.entities.album.CommentEntity
import com.example.vinilos.data.local.entities.album.TrackEntity
import com.example.vinilos.data.model.album.Album
import com.example.vinilos.data.model.album.Comment
import com.example.vinilos.data.model.album.DetailedAlbum
import com.example.vinilos.data.model.album.Track
import com.example.vinilos.data.network.models.network.AlbumNetwork
import com.example.vinilos.data.network.models.network.CommentNetwork
import com.example.vinilos.data.network.models.network.TrackNetwork
import com.example.vinilos.data.model.album.TrackRequest
import com.example.vinilos.data.network.models.network.AddTrackRequest


fun AlbumNetwork.asEntity() = AlbumEntity(
    id = id,
    cover = cover,
    name = name,
    releaseDate = releaseDate,
    description = description,
    genre = genre,
    recordLabel = recordLabel
)

fun TrackNetwork.asEntity(albumId: Int) = TrackEntity(
    id = id,
    duration = duration,
    name = name,
    albumId = albumId
)

fun CommentNetwork.asEntity(albumId: Int) = CommentEntity(
    id = id,
    description = description,
    rating = rating,
    albumId = albumId
)

fun AlbumNetwork.asUIDetailedModel() = DetailedAlbum(
    id = id,
    cover = cover,
    name = name,
    releaseDate = releaseDate,
    tracks = tracks.map { it.asUIModel() },
    comments = comments.map { it.asUIModel() }
)

fun TrackNetwork.asUIModel() = Track(
    id = id,
    duration = duration,
    name = name
)

fun AddTrackRequest.asUIModel() = TrackRequest(
    name = duration,
    duration = name
)
fun CommentNetwork.asUIModel() = Comment(
    id = id,
    description = description,
    rating = rating
)

fun AlbumWithTrackAndComment.asUIModel() = DetailedAlbum(
    id = albumEntity.id,
    cover = albumEntity.cover,
    name = albumEntity.name,
    releaseDate = albumEntity.releaseDate,
    tracks = trackEntity.map { it.asUIModel() },
    comments = commentEntity.map { it.asUIModel() }
)

fun TrackEntity.asUIModel() = Track(
    id = id,
    duration = duration,
    name = name
)


fun CommentEntity.asUIModel() = Comment(
    id = id,
    description = description,
    rating = rating
)

fun AlbumNetwork.asUIModel() = Album(
    id = id,
    cover = cover,
    name = name,
    releaseDate = releaseDate,
    description = description,
    genre = genre,
    recordLabel = recordLabel,
    comments = listOf(),
    tracks = listOf(),
)


fun com.example.vinilos.data.model.musician.Album.asNetworkModel(): AlbumNetwork {
    return AlbumNetwork(
        id = 0,
        name = name,
        cover = cover,
        releaseDate = releaseDate,
        description = description,
        genre = genre,
        recordLabel = recordLabel,
        comments = listOf<CommentNetwork>(),
        tracks =listOf<TrackNetwork>(),
    )
}
