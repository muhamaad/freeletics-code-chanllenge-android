package com.zarea.freeleticscodechanllenge.ui

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
import com.zarea.freeleticscodechanllenge.model.RepoResponse
import com.zarea.freeleticscodechanllenge.repository.DataRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by zarea at 2021
 */
@HiltViewModel
class RepoListViewModel @Inject constructor(private val dataRepo: DataRepo) : ViewModel() {
    val adapter = RepoAdapter()
    val refreshing = ObservableBoolean(false)

    fun fetchData() {
        viewModelScope.launch {
            dataRepo.getData().collect {
                setupData(it)
                refreshing.set(false)
            }
        }
    }

    private fun setupData(repoResponse: List<RepoResponse>) {
        val list = emptyList<RepoItemViewModel>().toMutableList()
        for (item in repoResponse) {
            list += RepoItemViewModel(item)
        }
        adapter.submitList(list)
    }

    fun getOnRefreshListener(): OnRefreshListener? {
        return OnRefreshListener {
            refreshing.set(true)
            fetchData()
        }
    }
}
