package com.zarea.freeleticscodechanllenge.database

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import com.zarea.freeleticscodechanllenge.model.RepoResponse
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test

/**
 * Created by zarea at 2021
 */
@SmallTest
@ExperimentalCoroutinesApi
class DatabaseTest {

    private lateinit var database: AppDatabase
    private lateinit var dao: RepoDao

    @Before
    fun init() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries().build()
        dao = database.RepoDao()
    }

    @Test
    fun insertRepoByIdTest() = runBlocking {
        val repoResponse = RepoResponse(1233, "repo name", 1234, false)
        dao.insertRepo(repoResponse)
        assertThat(dao.getRepoById(1233)).isEqualTo(repoResponse)
    }

    @Test
    fun insertRepoListTest() = runBlocking {
        val repoResponse = RepoResponse(1234, "repo name", 1234, false)
        val repoResponse1 = RepoResponse(1235, "repo name", 1234, false)
        val repoResponse2 = RepoResponse(1236, "repo name", 1234, false)
        val repoResponse3 = RepoResponse(1237, "repo name", 1234, false)
        val list = ArrayList<RepoResponse>()
        list.add(repoResponse)
        list.add(repoResponse2)
        list.add(repoResponse3)
        list.add(repoResponse1)
        dao.insertRepoList(list)
        assertThat(dao.getCount()).isEqualTo(4)
    }

    @Test
    fun deleteRepoTest() = runBlocking {
        val repoResponse = RepoResponse(1233, "testname", 1234, false)
        dao.insertRepo(repoResponse)
        dao.deleteRepoById(1233)
        assertThat(dao.getCount()).isEqualTo(0)
    }

    @Test
    fun updateBookmarkedTest() = runBlocking {
        val repoResponse = RepoResponse(1233, "testname", 1234, false)
        dao.insertRepo(repoResponse)
        dao.updateBookmarked(true, 1233)
        assertThat(dao.getRepoById(1233).bookmarked).isTrue()
    }

    @After
    fun closeDatabase() {
        database.close()
    }
}
