package com.example.vinilos.data.local.collector

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "collector")
data class CollectorEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "email") val email: String,
    @ColumnInfo(name = "telephone") val telephone: String,
)

@Entity(tableName = "collector_comment")
data class CollectorCommentEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "rating") val rating: Int,
    @ColumnInfo(name = "collectorId") val collectorId: Int)


@Entity(tableName = "performers")
data class PerformersEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "image") val image: String,
    @ColumnInfo(name = "collectorId") val collectorId: Int)
