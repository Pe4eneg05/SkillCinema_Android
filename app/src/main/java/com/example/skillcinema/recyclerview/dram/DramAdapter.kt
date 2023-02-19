package com.example.skillcinema.recyclerview.dram

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.skillcinema.databinding.MovieItemBinding
import com.example.skillcinema.entity.FilmsSerDramDet
import com.example.skillcinema.recyclerview.MovieViewHolder

class DramAdapter : RecyclerView.Adapter<MovieViewHolder>() {

    private var data: List<FilmsSerDramDet> = emptyList()

    fun setData(data: List<FilmsSerDramDet>){
        this.data = data
        notifyDataSetChanged()
    }

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
        val item = data.getOrNull(position)
        with(holder.binding){
            textTitle.text = item?.nameRu ?: ""
            textGenre.text = item?.genres?.first()?.genre
            if (item?.ratingKinopoisk == null || item.ratingKinopoisk.toString() == "") textRating.isVisible = false
            else textRating.text = item.ratingKinopoisk.toString()
            item?.let {
                Glide.with(poster.context)
                    .load(it.posterUrlPreview)
                    .into(poster)
            }
        }
    }

    override fun getItemCount(): Int = 20
}
