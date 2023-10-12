package com.hifit.android.mafit.binding

import android.content.Context
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import com.hifit.android.mafit.R
import com.hifit.android.mafit.data.model.home.WorkoutInfoData
import timber.log.Timber


@BindingAdapter("bind:isVisible")
fun View.setVisible(isVisible: Boolean) {
    this.isVisible = isVisible
}

@BindingAdapter("bind:gender")
fun TextView.setGender(gender: String?) {
    text = when (gender) {
        "MALE" -> "남성"
        "FEMALE" -> "여성"
        else -> ""
    }
}

@BindingAdapter("bind:workoutInfo")
fun TextView.setText(workOutInfo: WorkoutInfoData?) {
    val remainNum = (workOutInfo?.targetStamp ?: 0) - (workOutInfo?.stamp ?: 0)
    this.text = this.context.getString(R.string.home_exercise_status_content).format(
        remainNum
    )
}

@BindingAdapter("bind:realNumFormatText")
fun TextView.setText(num: Double?) {
    num?.let {
        this.text = String.format("%.1f", num)
    }
}





