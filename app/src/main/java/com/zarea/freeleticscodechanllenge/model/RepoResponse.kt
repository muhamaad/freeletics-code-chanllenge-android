package com.zarea.freeleticscodechanllenge.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Created by zarea at 2021
 */
@Entity
@JsonClass(generateAdapter = true)
data class RepoResponse(
    @PrimaryKey
    @Json(name = "id")
    val id: Long,

    @ColumnInfo(name = "name")
    @Json(name = "name")
    val name: String,

    @ColumnInfo(name = "stargazers_count")
    @Json(name = "stargazers_count")
    val stargazersCount: Int,

    @ColumnInfo(name = "bookmarked")
    @Transient
    val bookmarked: Boolean = false

)
