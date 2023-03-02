package com.example.skillcinema.recyclerview.directors

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
import com.example.skillcinema.entity.ActorsList
import com.example.skillcinema.entity.FilmsSerDramDet
import com.example.skillcinema.recyclerview.MovieViewHolder
import com.example.skillcinema.recyclerview.countDirectors

class DirectorsAdapter (val onClick: (ActorsList) -> Unit, var flag: Boolean) : RecyclerView.Adapter<DirectorViewHolder>() {

    val data: MutableList<ActorsList> = mutableListOf()

    fun setData(data: List<ActorsList>){
        data.forEach {
            if (it.professionKey != "ACTOR") this.data.add(it)
        }
        countDirectors = this.data.size
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DirectorViewHolder {
        return DirectorViewHolder(
            ActorItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: DirectorViewHolder, position: Int) {
        val item = data.getOrNull(position)
        with(holder.binding){
            actorName.text = item?.nameRu ?: item?.nameEn
            actorRole.text = item?.professionText?.substring(0, item.professionText.length - 1)
            Glide.with(actorImage.context)
                .load(item?.posterUrl)
                .into(actorImage)
        }
        holder.binding.root.setOnClickListener {
            item?.let {
                onClick(item)
            }
        }
    }

    override fun getItemCount(): Int {
        return if (flag) {
            if (data.size > 20) 20
            else data.size
        }
        else data.size
    }
}

class DirectorViewHolder(val binding: ActorItemBinding) : RecyclerView.ViewHolder(binding.root)


