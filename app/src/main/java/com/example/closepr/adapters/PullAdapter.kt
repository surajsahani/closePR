package com.example.closepr.adapters


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.closepr.databinding.PullItemRowBinding
import com.example.closepr.models.Pull
import javax.inject.Inject


class PullAdapter @Inject constructor() :
    PagingDataAdapter<Pull, PullAdapter.PullViewHolder>(PullDiffUtil()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PullViewHolder {
        return PullViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: PullViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }


    class PullViewHolder private constructor(private val binding: PullItemRowBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(pull: Pull?) {
            binding.pull = pull
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): PullViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = PullItemRowBinding.inflate(layoutInflater, parent, false)
                return PullViewHolder(binding)
            }
        }
    }
}

class PullDiffUtil : DiffUtil.ItemCallback<Pull>() {
    override fun areItemsTheSame(oldPull: Pull, newPull: Pull): Boolean {
        return oldPull === newPull
    }

    override fun areContentsTheSame(oldPull: Pull, newPull: Pull): Boolean {
        return oldPull == newPull
    }

}