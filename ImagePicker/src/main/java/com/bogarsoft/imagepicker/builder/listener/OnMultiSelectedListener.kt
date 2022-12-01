package com.bogarsoft.imagepicker.builder.listener

import android.net.Uri

interface OnMultiSelectedListener {
    fun onSelected(uriList: List<Uri>)
}