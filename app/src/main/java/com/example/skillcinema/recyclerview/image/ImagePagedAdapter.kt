package com.example.skillcinema.recyclerview.image

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.skillcinema.databinding.ImageItemBinding
import com.example.skillcinema.databinding.MovieItemBinding
import com.example.skillcinema.entity.FilmsPremier
import com.example.skillcinema.entity.FilmsSerDramDet
import com.example.skillcinema.entity.ImageList
import com.example.skillcinema.entity.PhotoItems
import com.example.skillcinema.recyclerview.MovieViewHolder

class ImagePagedAdapter : PagingDataAdapter<PhotoItems, ImageViewHolder>(DiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        return ImageViewHolder(
            ImageItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val item = getItem(position)
        with(holder.binding) {
            item?.let {
                Glide.with(imageItem.context)
                    .load(it.imageUrl)
                    .into(imageItem)
            }
        }
    }
}

class DiffUtilCallback : DiffUtil.ItemCallback<PhotoItems>() {
    override fun areItemsTheSame(oldItem: PhotoItems, newItem: PhotoItems): Boolean =
        oldItem.imageUrl == newItem.imageUrl

    override fun areContentsTheSame(oldItem: PhotoItems, newItem: PhotoItems): Boolean = oldItem == newItem
}

class ImageViewHolder(val binding: ImageItemBinding) : RecyclerView.ViewHolder(binding.root)