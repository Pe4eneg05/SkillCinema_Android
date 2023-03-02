package com.example.skillcinema.recyclerview.allfilms

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
import com.example.skillcinema.entity.FilmList
import com.example.skillcinema.entity.FilmsAll
import com.example.skillcinema.entity.FilmsPremier
import com.example.skillcinema.entity.FilmsSerDramDet
import com.example.skillcinema.recyclerview.MovieViewHolder
import com.example.skillcinema.recyclerview.typeScreen

class AllFilmsPagedAdapter /*(val onClick: (FilmsSerDramDet) -> Unit)*/ : PagingDataAdapter<FilmsAll, MovieSearchItemViewHolder>(DiffUtilCallback()) {

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
            nameMovieSearchItem.text = item?.nameRu ?: item?.nameOriginal ?: ""
            infoMovieSearchItem.text = "${item?.year}, ${item?.genres?.first()?.genre}"
            if (item?.ratingKinopoisk == null || item.ratingKinopoisk.toString() == "") textRatingSearchItem.isVisible = false
            else textRatingSearchItem.text = item.ratingKinopoisk.toString()
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

class DiffUtilCallback : DiffUtil.ItemCallback<FilmsAll>() {
    override fun areItemsTheSame(oldItem: FilmsAll, newItem: FilmsAll): Boolean =
        oldItem.kinopoiskId == newItem.kinopoiskId

    override fun areContentsTheSame(oldItem: FilmsAll, newItem: FilmsAll): Boolean = oldItem == newItem
}

class MovieSearchItemViewHolder(val binding: MovieSearchItemBinding) : RecyclerView.ViewHolder(binding.root)


