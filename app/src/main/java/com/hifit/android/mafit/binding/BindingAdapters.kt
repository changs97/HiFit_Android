package com.hifit.android.mafit.binding

import android.view.View
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter


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





