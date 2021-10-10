package com.zarea.freeleticscodechanllenge.ui

import android.view.View
import com.zarea.freeleticscodechanllenge.model.RepoResponse

/**
 * Created by zarea at 2021
 */
class RepoItemViewModel(private val repoItem: RepoResponse) {
    fun getName(): String {
        return "Repo Name: " + repoItem.name
    }

    fun getStarsCount(): String {
        return "Star Count: " + repoItem.stargazersCount
    }

    fun isBookmarked(): Boolean {
        return repoItem.bookmarked
    }

    fun getId(): Long {
        return repoItem.id
    }

    fun onItemClicked(view: View) {
        RepoDetailsActivity.start(view.context, getId())
    }
}
