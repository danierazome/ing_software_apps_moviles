package com.example.vinilos.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.vinilos.data.local.collector.CollectorCommentEntity
import com.example.vinilos.data.local.collector.CollectorEntity
import com.example.vinilos.data.local.collector.CollectorWithPerformerAndComment
import com.example.vinilos.data.local.collector.PerformersEntity

@Dao
interface CollectorDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(collectorEntity: CollectorEntity)

    @Transaction
    @Query("SELECT * FROM collector WHERE id = :id")
    suspend fun getCollectorWithTrackAndComment(id: Int): CollectorWithPerformerAndComment

}

@Dao
interface PerformerDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(performersEntity: PerformersEntity)
}

@Dao
interface CollectorCommentDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(collectorCommentEntity: CollectorCommentEntity)
}
