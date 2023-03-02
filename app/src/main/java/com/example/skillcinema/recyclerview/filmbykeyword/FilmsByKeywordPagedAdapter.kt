package com.example.skillcinema.recyclerview.filmbykeyword

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.skillcinema.databinding.MovieItemBinding
import com.example.skillcinema.databinding.MovieSearchItemBinding
import com.example.skillcinema.entity.*
import com.example.skillcinema.recyclerview.MovieViewHolder
import com.example.skillcinema.recyclerview.typeScreen

class FilmsByKeywordPagedAdapter /*(val onClick: (FilmsSerDramDet) -> Unit)*/ : PagingDataAdapter<FilmsByKeyword, MovieSearchItemViewHolder>(DiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieSearchItemViewHolder {
        return MovieSearchItemViewHolder(
            MovieSearchItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MovieSearchItemViewHolder, position: Int) {
        val item = getItem(position)
        with(holder.binding) {
            nameMovieSearchItem.text = item?.nameRu ?: ""
            infoMovieSearchItem.text = "${item?.year}, ${item?.genres?.first()?.genre}"
            if (item?.rating == null || item.rating == "") textRatingSearchItem.isVisible = false
            else textRatingSearchItem.text = item.rating.toString()
            item?.let {
                Glide.with(movieSearchItem.context)
                    .load(it.posterUrlPreview)
                    .into(movieSearchItem)
            }
        }
//        holder.binding.root.setOnClickListener {
//            item?.let {
//                onClick(item)
//                typeScreen = false
//            }
//        }
    }

}

class DiffUtilCallback : DiffUtil.ItemCallback<FilmsByKeyword>() {
    override fun areItemsTheSame(oldItem: FilmsByKeyword, newItem: FilmsByKeyword): Boolean =
        oldItem.filmId == newItem.filmId

    override fun areContentsTheSame(oldItem: FilmsByKeyword, newItem: FilmsByKeyword): Boolean = oldItem == newItem
}

class MovieSearchItemViewHolder(val binding: MovieSearchItemBinding) : RecyclerView.ViewHolder(binding.root)


