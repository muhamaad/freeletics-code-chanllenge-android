package com.zarea.freeleticscodechanllenge.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.zarea.freeleticscodechanllenge.model.RepoResponse
import kotlinx.coroutines.flow.Flow

/**
 * Created by zarea at 2021
 */
@Dao
interface RepoDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertRepoList(repo: List<RepoResponse>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertRepo(repo: RepoResponse)

    @Query("SELECT * FROM RepoResponse")
    fun getRepoList(): Flow<List<RepoResponse>>

    @Query("SELECT * FROM RepoResponse WHERE id = :id")
    fun getRepoById(id: Long): RepoResponse

    @Query("SELECT COUNT(id) FROM RepoResponse")
    fun getCount(): Int

    @Query("UPDATE RepoResponse SET bookmarked=:bookmarked WHERE id = :id")
    fun updateBookmarked(bookmarked: Boolean?, id: Long)

    @Query("DELETE FROM RepoResponse WHERE id = :id")
    fun deleteRepoById(id: Long)
}
