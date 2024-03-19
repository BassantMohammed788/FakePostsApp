package com.example.fakepostsapp.presentation.view.user_posts.view

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.fakepostsapp.databinding.PostItemBinding
import com.example.fakepostsapp.presentation.model.PostEntityUi


class UserPostsAdapter (private val listener: (String) -> Unit) :
    ListAdapter<PostEntityUi, UserPostsAdapter.PostViewHolder>(RecyclerDiffUtil()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val inflater: LayoutInflater =
            parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val binding = PostItemBinding.inflate(inflater, parent, false)
        return PostViewHolder(binding)
    }


    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.binding.tvUserId.text = currentItem.userId.toString()
        holder.binding.tvTitle.text = currentItem.title.toString()
        holder.binding.tvBody.text = currentItem.body
        holder.binding.cvAlbum.setOnClickListener {
            listener(currentItem.id.toString())
        }
    }
    inner class PostViewHolder(val binding: PostItemBinding) : RecyclerView.ViewHolder(binding.root)

}

class RecyclerDiffUtil : DiffUtil.ItemCallback<PostEntityUi>() {
    override fun areItemsTheSame(oldItem: PostEntityUi, newItem: PostEntityUi): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: PostEntityUi, newItem: PostEntityUi): Boolean {
        return oldItem == newItem
    }
}