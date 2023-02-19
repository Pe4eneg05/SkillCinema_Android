package com.example.skillcinema.recyclerview.popular

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.skillcinema.databinding.MovieItemBinding
import com.example.skillcinema.entity.FilmsSerDramDet
import com.example.skillcinema.entity.FilmsTopPopular
import com.example.skillcinema.recyclerview.MovieViewHolder

class PopularAdapter : RecyclerView.Adapter<MovieViewHolder>() {

    private var data: List<FilmsTopPopular> = emptyList()

    fun setData(data: List<FilmsTopPopular>){
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
            if (item?.rating == null || item.rating == "") textRating.isVisible = false
            else textRating.text = item.rating
            item?.let {
                Glide.with(poster.context)
                    .load(it.posterUrlPreview)
                    .into(poster)
            }
        }
    }

    override fun getItemCount(): Int = 20
}

