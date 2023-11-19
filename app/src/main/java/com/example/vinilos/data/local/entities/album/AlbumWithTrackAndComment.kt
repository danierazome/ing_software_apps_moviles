package com.example.vinilos.data.local.entities.album

import androidx.room.Embedded
import androidx.room.Relation

data class AlbumWithTrackAndComment (
    @Embedded val albumEntity: AlbumEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "albumId"
    )
    val trackEntity: List<TrackEntity>,
    @Relation(
        parentColumn = "id",
        entityColumn = "albumId"
    )
    val commentEntity: List<CommentEntity>
)