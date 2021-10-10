package com.zarea.freeleticscodechanllenge.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.zarea.freeleticscodechanllenge.R
import com.zarea.freeleticscodechanllenge.databinding.ViewRepoItemBinding

/**
 * Created by zarea at 2021
 */
class RepoAdapter : ListAdapter<RepoItemViewModel, RepoAdapter.ViewHolder>(RepoItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.view_repo_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding!!.viewModel = getItem(position)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = DataBindingUtil.bind<ViewRepoItemBinding>(itemView)
    }

    class RepoItemDiffCallback : DiffUtil.ItemCallback<RepoItemViewModel>() {

        override fun areItemsTheSame(
            oldItem: RepoItemViewModel,
            newItem: RepoItemViewModel
        ): Boolean {
            return oldItem.getId() == newItem.getId()
        }

        override fun areContentsTheSame(
            oldItem: RepoItemViewModel,
            newItem: RepoItemViewModel
        ): Boolean {
            return oldItem.getId() == newItem.getId() && oldItem.isBookmarked() == newItem.isBookmarked()
        }
    }
}
