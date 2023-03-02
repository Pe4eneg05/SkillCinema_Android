package com.example.skillcinema.recyclerview.premier

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.skillcinema.databinding.MovieItemBinding
import com.example.skillcinema.entity.FilmsPremier
import com.example.skillcinema.recyclerview.MovieViewHolder
import com.example.skillcinema.recyclerview.typeScreen

class PremierPagedAdapter (val onClick: (FilmsPremier) -> Unit) : RecyclerView.Adapter<MovieViewHolder>() {

    private var data: List<FilmsPremier> = emptyList()

    fun setData(data: List<FilmsPremier>){
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
                typeScreen = false
            }
        }
    }

    override fun getItemCount(): Int = data.size

}