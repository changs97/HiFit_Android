package com.hifit.android.mafit.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class ProductItem(val img: String, val brand: String, val name: String, val coin: Int) :
    Parcelable
