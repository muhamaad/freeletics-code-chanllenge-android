package com.zarea.freeleticscodechanllenge.ui

import android.view.View
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zarea.freeleticscodechanllenge.repository.DataRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by zarea at 2021
 */
@HiltViewModel
class RepoDetailsViewModel @Inject constructor(private val dataRepo: DataRepo) : ViewModel() {
    var id: Long = 0
    val name = ObservableField<String>()
    val starCount = ObservableField<String>()
    val bookmarkedButtonText = ObservableField<String>()
    val bookmarked = ObservableBoolean(false)

    fun fetchData() {
        viewModelScope.launch {
            val repo = dataRepo.getRepoById(id)
            name.set("Repo Name: " + repo.name)
            starCount.set("Star Count: " + repo.stargazersCount)
            bookmarked.set(repo.bookmarked)
            changeBookmarkedText()
        }
    }

    fun onBookmarkedClicked(view: View) {
        viewModelScope.launch {
            dataRepo.updateBookmarked(!bookmarked.get(), id)
            bookmarked.set(!bookmarked.get())
            changeBookmarkedText()
        }
    }

    private fun changeBookmarkedText() {
        bookmarkedButtonText.set(
            if (bookmarked.get()) {
                "remove bookmark"
            } else {
                "add to bookmark"
            }
        )
    }
}
