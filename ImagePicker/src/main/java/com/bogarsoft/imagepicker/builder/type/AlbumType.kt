package com.bogarsoft.imagepicker.builder.type

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
enum class AlbumType : Parcelable {
    DRAWER,
    DROP_DOWN
}