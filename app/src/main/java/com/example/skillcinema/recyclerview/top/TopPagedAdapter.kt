package com.example.skillcinema.recyclerview.top

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.bumptech.glide.Glide
import com.example.skillcinema.databinding.MovieItemBinding
import com.example.skillcinema.entity.FilmsPremier
import com.example.skillcinema.entity.FilmsTopPopular
import com.example.skillcinema.recyclerview.MovieViewHolder
import com.example.skillcinema.recyclerview.typeScreen

class TopPagedAdapter (val onClick: (FilmsTopPopular) -> Unit) : PagingDataAdapter<FilmsTopPopular, MovieViewHolder>(DiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            MovieItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val item = getItem(position)
        with(holder.binding) {
            textTitle.text = item?.nameRu ?: ""
            textGenre.text = item?.genres?.first()?.genre
            if (item?.rating == null || item.rating == "") textRating.isVisible = false
            else textRating.text = item.rating
            item?.let {
                Glide.with(poster.context)
                    .load(it.posterUrlPreview)
                    .into(poster)
            }
        }
        holder.binding.root.setOnClickListener {
            item?.let {
                onClick(item)
                typeScreen = false
            }
        }
    }
}

class DiffUtilCallback : DiffUtil.ItemCallback<FilmsTopPopular>() {
    override fun areItemsTheSame(oldItem: FilmsTopPopular, newItem: FilmsTopPopular): Boolean =
        oldItem.filmId == newItem.filmId

    override fun areContentsTheSame(oldItem: FilmsTopPopular, newItem: FilmsTopPopular): Boolean = oldItem == newItem
}