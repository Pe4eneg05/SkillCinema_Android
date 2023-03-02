package com.example.skillcinema.recyclerview.similar

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.skillcinema.databinding.MovieItemBinding
import com.example.skillcinema.entity.FilmsPremier
import com.example.skillcinema.entity.FilmsSerDramDet
import com.example.skillcinema.entity.SimilarItems
import com.example.skillcinema.recyclerview.MovieViewHolder

class SimilarAdapter (val onClick: (SimilarItems) -> Unit) : RecyclerView.Adapter<MovieViewHolder>() {

    private var data: List<SimilarItems> = emptyList()

    fun setData(data: List<SimilarItems>){
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
            textRating.isVisible = false
            item?.let {
                Glide.with(poster.context)
                    .load(it.posterUrlPreview)
                    .into(poster)
            }
        }
        holder.binding.root.setOnClickListener {
            item?.let {
                onClick(item)
            }
        }
    }

    override fun getItemCount(): Int = data.size
}

class MovieViewHolder(val binding: MovieItemBinding) : RecyclerView.ViewHolder(binding.root)