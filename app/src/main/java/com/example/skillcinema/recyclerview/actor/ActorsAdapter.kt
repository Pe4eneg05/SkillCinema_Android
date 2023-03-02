package com.example.skillcinema.recyclerview.actor

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
import com.example.skillcinema.entity.FilmsTopPopular
import com.example.skillcinema.recyclerview.MovieViewHolder
import com.example.skillcinema.recyclerview.countActors
import com.example.skillcinema.recyclerview.typeScreen

class ActorsAdapter (val onClick: (ActorsList) -> Unit, var flag: Boolean) : RecyclerView.Adapter<ActorViewHolder>() {

    private var data: MutableList<ActorsList> = mutableListOf()

    fun setData(data: List<ActorsList>){
        data.forEach {
            if (it.professionKey == "ACTOR") this.data.add(it)
        }
        countActors = this.data.size
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorViewHolder {
        return ActorViewHolder(
            ActorItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ActorViewHolder, position: Int) {
        val item = data.getOrNull(position)
        with(holder.binding){
            actorName.text = item?.nameRu ?: item?.nameEn
            actorRole.text = item?.description
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

class ActorViewHolder(val binding: ActorItemBinding) : RecyclerView.ViewHolder(binding.root)


