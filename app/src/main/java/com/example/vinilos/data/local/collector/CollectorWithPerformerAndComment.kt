package com.example.vinilos.data.local.collector

import androidx.room.Embedded
import androidx.room.Relation

data class CollectorWithPerformerAndComment (
    @Embedded val collectorEntity: CollectorEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "collectorId"
    )
    val performerEntity: List<PerformersEntity>,
    @Relation(
        parentColumn = "id",
        entityColumn = "collectorId"
    )
    val collectorCommentEntity: List<CollectorCommentEntity>
)