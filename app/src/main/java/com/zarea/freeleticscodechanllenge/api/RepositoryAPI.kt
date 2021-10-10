package com.zarea.freeleticscodechanllenge.api

import com.zarea.freeleticscodechanllenge.model.RepoResponse
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by zarea at 2021
 */
interface RepositoryAPI {
    @GET("orgs/square/repos")
    suspend fun squareRepo(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int,
    ): List<RepoResponse>
}
