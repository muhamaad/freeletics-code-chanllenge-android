package com.zarea.freeleticscodechanllenge.repository

import com.zarea.freeleticscodechanllenge.api.RepositoryAPI
import com.zarea.freeleticscodechanllenge.database.RepoDao
import com.zarea.freeleticscodechanllenge.model.RepoResponse
import com.zarea.freeleticscodechanllenge.utils.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Created by zarea at 2021
 */
class DataRepo @Inject constructor(
    private val repositoryAPI: RepositoryAPI,
    private val repoDao: RepoDao
) {

    suspend fun getData(): Flow<List<RepoResponse>> {
        return withContext(Dispatchers.IO) {
            var list = emptyList<RepoResponse>()
            try {
                list = repositoryAPI.squareRepo(Constants.PAGE, Constants.PER_PAGE)
            } catch (e: Exception) {
                e.printStackTrace()
            }
            if (list.isNotEmpty()) {
                saveData(list)
            }
            return@withContext repoDao.getRepoList()
        }
    }

    fun getSavedData(): Flow<List<RepoResponse>> {
        return repoDao.getRepoList()
    }

    fun saveData(repoResponseList: List<RepoResponse>) {
        repoDao.insertRepoList(repoResponseList)
    }

    fun getCount(): Int {
        return repoDao.getCount()
    }

    suspend fun updateBookmarked(bookmarked: Boolean, id: Long) {
        withContext(Dispatchers.IO) {
            repoDao.updateBookmarked(bookmarked, id)
        }
    }

    suspend fun getRepoById(id: Long): RepoResponse {
        return withContext(Dispatchers.IO) {
            return@withContext repoDao.getRepoById(id)
        }
    }

    fun deleteRepoById(id: Long) {
        repoDao.deleteRepoById(id)
    }

    suspend fun insertRepo(repo: RepoResponse) {
        withContext(Dispatchers.IO) {
            repoDao.insertRepo(repo)
        }
    }
}
