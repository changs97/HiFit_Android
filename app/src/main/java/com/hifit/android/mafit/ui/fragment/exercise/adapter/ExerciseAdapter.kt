package com.hifit.android.mafit.ui.fragment.exercise.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.hifit.android.mafit.data.model.ExerciseItem

class ExerciseAdapter(private val listener: ExerciseAdapterListener) :
    ListAdapter<ExerciseItem, ExerciseViewHolder>(object : DiffUtil.ItemCallback<ExerciseItem>() {
        override fun areItemsTheSame(oldItem: ExerciseItem, newItem: ExerciseItem): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: ExerciseItem, newItem: ExerciseItem): Boolean {
            return oldItem == newItem
        }
    }) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseViewHolder {
        return ExerciseViewHolder(parent, listener)
    }

    override fun onBindViewHolder(holder: ExerciseViewHolder, position: Int) {
        holder.bind(currentList[position])
    }
}


