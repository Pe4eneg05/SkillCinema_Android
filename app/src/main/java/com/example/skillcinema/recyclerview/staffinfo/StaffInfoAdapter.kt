package com.example.skillcinema.recyclerview.staffinfo

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
import com.example.skillcinema.entity.*
import com.example.skillcinema.recyclerview.MovieViewHolder
import com.example.skillcinema.recyclerview.countActors
import com.example.skillcinema.recyclerview.typeScreen

class StaffInfoAdapter (val onClick: (MovieInfo) -> Unit) : RecyclerView.Adapter<StaffInfoViewHolder>() {

    private var data: List<MovieInfo> = emptyList()

    fun setData(data: List<MovieInfo>){
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StaffInfoViewHolder {
        return StaffInfoViewHolder(
            MovieItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: StaffInfoViewHolder, position: Int) {
        val item = data.getOrNull(position)
        with(holder.binding){
            textTitle.text = item?.nameRu ?: item?.nameEn ?: ""
            textRating.text = item?.ratingKinopoisk.toString()
            textGenre.text = item?.genres?.first()?.genre
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

    override fun getItemCount(): Int = 10
}

class StaffInfoViewHolder(val binding: MovieItemBinding) : RecyclerView.ViewHolder(binding.root)


