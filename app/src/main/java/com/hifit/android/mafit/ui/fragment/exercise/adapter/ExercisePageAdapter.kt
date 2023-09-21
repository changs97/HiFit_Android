package com.hifit.android.mafit.ui.fragment.exercise.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.hifit.android.mafit.data.model.ExerciseItem

class ExercisePageAdapter(private val listener: ExerciseAdapterListener) :
    ListAdapter<ArrayList<ExerciseItem>, ExercisePageViewHolder>(object : DiffUtil.ItemCallback<ArrayList<ExerciseItem>>() {
        override fun areItemsTheSame(oldItem: ArrayList<ExerciseItem>, newItem: ArrayList<ExerciseItem>): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: ArrayList<ExerciseItem>, newItem: ArrayList<ExerciseItem>): Boolean {
            return oldItem == newItem
        }
    }) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExercisePageViewHolder {
        return ExercisePageViewHolder(parent, listener)
    }

    override fun onBindViewHolder(holder: ExercisePageViewHolder, position: Int) {
        holder.bind(currentList[position])
    }
}


