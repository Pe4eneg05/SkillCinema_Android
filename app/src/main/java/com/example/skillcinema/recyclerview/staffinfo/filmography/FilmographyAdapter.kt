package com.example.skillcinema.recyclerview.staffinfo.filmography

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import com.bumptech.glide.Glide
import com.example.skillcinema.databinding.ActorItemBinding
import com.example.skillcinema.databinding.MovieItemBinding
import com.example.skillcinema.databinding.MovieSearchItemBinding
import com.example.skillcinema.entity.*
import com.example.skillcinema.recyclerview.MovieViewHolder
import com.example.skillcinema.recyclerview.countActors
import com.example.skillcinema.recyclerview.typeScreen

class FilmographyAdapter /*(val onClick: (MovieInfo) -> Unit)*/ : RecyclerView.Adapter<StaffInfoViewHolder>() {

    private var data: List<MovieInfo> = emptyList()

    fun setData(data: List<MovieInfo>){
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StaffInfoViewHolder {
        return StaffInfoViewHolder(
            MovieSearchItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: StaffInfoViewHolder, position: Int) {
        val item = data.getOrNull(position)
        with(holder.binding){
            nameMovieSearchItem.text = item?.nameRu ?: item?.nameEn ?: ""
            textRatingSearchItem.text = item?.ratingKinopoisk.toString()
            infoMovieSearchItem.text = "${item?.year}, ${item?.genres?.first()?.genre}"
            item?.let {
                Glide.with(movieSearchItem.context)
                    .load(it.posterUrlPreview)
                    .into(movieSearchItem)
            }
        }
//        holder.binding.root.setOnClickListener {
//            item?.let {
//                onClick(item)
//            }
//        }
    }

    override fun getItemCount(): Int = data.size
}

class StaffInfoViewHolder(val binding: MovieSearchItemBinding) : RecyclerView.ViewHolder(binding.root)


