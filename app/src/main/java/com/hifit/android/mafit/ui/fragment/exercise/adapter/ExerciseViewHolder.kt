package com.hifit.android.mafit.ui.fragment.exercise.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hifit.android.mafit.R
import com.hifit.android.mafit.data.model.ExerciseItem
import com.hifit.android.mafit.databinding.ItemExerciseBinding


class ExerciseViewHolder(
    parent: ViewGroup, private val listener: ExerciseAdapterListener
) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.item_exercise, parent, false)
) {
    private val binding = ItemExerciseBinding.bind(itemView)

    fun bind(item: ExerciseItem) {
        binding.root.setOnClickListener {
            listener.onExerciseClicked(item.link)
        }

        val id: String = item.link
            .substring(item.link.lastIndexOf("/") + 1)

        val url = "https://img.youtube.com/vi/$id/mqdefault.jpg"

        Glide.with(itemView.context)
            .load(url)
            .centerCrop()
            .into(binding.itemExerciseImg)

    }
}