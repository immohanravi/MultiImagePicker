package com.bogarsoft.imagepicker.builder.type

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
enum class SelectType : Parcelable {
    SINGLE, MULTI
}