package com.bogarsoft.imagepicker.builder.listener

import android.net.Uri

interface OnSelectedListener {
    fun onSelected(uri: Uri)
}