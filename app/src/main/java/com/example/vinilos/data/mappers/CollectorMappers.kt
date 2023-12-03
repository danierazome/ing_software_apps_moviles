package com.example.vinilos.data.mappers

import com.example.vinilos.data.local.collector.CollectorCommentEntity
import com.example.vinilos.data.local.collector.CollectorEntity
import com.example.vinilos.data.local.collector.CollectorWithPerformerAndComment
import com.example.vinilos.data.local.collector.PerformersEntity
import com.example.vinilos.data.model.Collector
import com.example.vinilos.data.model.Comment
import com.example.vinilos.data.model.collector.CollectorComment
import com.example.vinilos.data.model.collector.DetailedCollector
import com.example.vinilos.data.model.collector.Performer
import com.example.vinilos.data.network.models.collectorNetwork.CollectorCommentNetwork
import com.example.vinilos.data.network.models.collectorNetwork.CollectorNetwork
import com.example.vinilos.data.network.models.collectorNetwork.PerformerNetwork


fun CollectorNetwork.asEntity() = CollectorEntity(
    id = id,
    email = email,
    name = name,
    telephone = telephone
)

fun PerformerNetwork.asEntity(collectorId: Int) = PerformersEntity(
    id = id,
    description = description,
    name = name,
    image = image,
    collectorId = collectorId,
)

fun CollectorCommentNetwork.asEntity(collectorId: Int) = CollectorCommentEntity(
    id = id,
    description = description,
    rating = rating,
    collectorId = collectorId,
)

fun CollectorNetwork.asUIDetailedModel() = DetailedCollector(
    id = id,
    email = email,
    name = name,
    telephone = telephone,
    performers = favoritePerformers.map { it.asUIModel() },
    collectorComments = comments.map { it.asUIModel() }
)

fun PerformerNetwork.asUIModel() = Performer(
    id = id,
    description = description,
    name = name,
    image = image
)


fun CollectorCommentNetwork.asUIModel() = CollectorComment(
    id = id,
    description = description,
    rating = rating
)

fun CollectorWithPerformerAndComment.asUIModel() = DetailedCollector(
    id = collectorEntity.id,
    email = collectorEntity.email,
    name = collectorEntity.name,
    telephone = collectorEntity.telephone,
    performers = performerEntity.map { it.asUIModel() },
    collectorComments = collectorCommentEntity.map { it.asUIModel() }
)

fun PerformersEntity.asUIModel() = Performer(
    id = id,
    description = description,
    name = name,
    image = image
)


fun CollectorCommentEntity.asUIModel() = CollectorComment(
    id = id,
    description = description,
    rating = rating
)

fun CollectorNetwork.asUIModel() = Collector(
    name = name,
    comments = comments.map { Comment(it.id, it.description, it.rating) },
    id = id,
)