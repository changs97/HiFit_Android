package com.hifit.android.mafit.ui.fragment.exercise.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hifit.android.mafit.R
import com.hifit.android.mafit.data.model.ExerciseItem
import com.hifit.android.mafit.databinding.ItemExerciseBinding

class ExerciseViewHolder(
    parent: ViewGroup, listener: ExerciseAdapterListener
) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.item_exercise, parent, false)
) {
    private val binding = ItemExerciseBinding.bind(itemView)

    init {
        // set 리스너
        binding.root.setOnClickListener {
            listener.onExerciseClicked("https://www.youtube.com/watch?v=PK3gAfasWpI")
        }
    }

    fun bind(item: ExerciseItem) {
        // set item
        Glide.with(itemView.context)
            .load(R.drawable.exercise_sample)
            .centerCrop()
            .into(binding.itemExerciseImg)
        binding.executePendingBindings()
    }
}