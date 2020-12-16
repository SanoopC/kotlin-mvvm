package com.exalture.atm.about

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.exalture.atm.databinding.GridViewItemBinding
import com.exalture.atm.network.ExaltureProjects

class PhotoGridAdapter(private val onClickListener: OnClickListener) :
    ListAdapter<ExaltureProjects, PhotoGridAdapter.ExaltureProjectViewModel>(DiffCallback) {

    class ExaltureProjectViewModel(private var binding: GridViewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(exaltureProjects: ExaltureProjects) {
            binding.property = exaltureProjects
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<ExaltureProjects>() {
        override fun areItemsTheSame(oldItem: ExaltureProjects, newItem: ExaltureProjects): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: ExaltureProjects, newItem: ExaltureProjects): Boolean {
            return oldItem.id == newItem.id
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExaltureProjectViewModel {
        return ExaltureProjectViewModel(GridViewItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: ExaltureProjectViewModel, position: Int) {
        val marsProperty = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(marsProperty)
        }
        holder.bind(marsProperty)
    }

    class OnClickListener(val clickListener: (exaltureProjects: ExaltureProjects) -> Unit) {
        fun onClick(exaltureProjects: ExaltureProjects) = clickListener(exaltureProjects)
    }
}