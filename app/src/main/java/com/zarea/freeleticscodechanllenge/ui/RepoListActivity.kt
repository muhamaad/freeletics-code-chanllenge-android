package com.zarea.freeleticscodechanllenge.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.zarea.freeleticscodechanllenge.R
import com.zarea.freeleticscodechanllenge.databinding.ActivityRepoListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RepoListActivity : AppCompatActivity() {

    private val viewModel: RepoListViewModel by viewModels()
    private lateinit var binding: ActivityRepoListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind()
        viewModel.fetchData()
    }

    private fun bind() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_repo_list)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
    }
}
