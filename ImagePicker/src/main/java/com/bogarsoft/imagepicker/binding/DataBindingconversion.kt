package com.bogarsoft.imagepicker.binding

import android.view.View
import androidx.databinding.BindingConversion


@BindingConversion
fun convertBooleanToVisibility(visible: Boolean) = if (visible) View.VISIBLE else View.GONE


