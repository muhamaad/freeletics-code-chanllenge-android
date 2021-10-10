package com.zarea.freeleticscodechanllenge.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.zarea.freeleticscodechanllenge.R
import com.zarea.freeleticscodechanllenge.databinding.ActivityRepoDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RepoDetailsActivity : AppCompatActivity() {
    private val viewModel: RepoDetailsViewModel by viewModels()
    private lateinit var binding: ActivityRepoDetailsBinding

    companion object {
        private const val EXTRA_REPO_ID = "EXTRA_REPO_ID"
        fun start(context: Context, repoId: Long) {
            val intent = Intent(context, RepoDetailsActivity::class.java)
            intent.putExtra(EXTRA_REPO_ID, repoId)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind()
        getInputData()
        viewModel.fetchData()
    }

    private fun bind() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_repo_details)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
    }

    private fun getInputData() {
        viewModel.id = intent.getLongExtra(EXTRA_REPO_ID, 0)
    }
}
