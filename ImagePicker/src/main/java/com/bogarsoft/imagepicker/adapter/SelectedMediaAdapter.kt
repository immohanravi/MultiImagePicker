package com.bogarsoft.imagepicker.adapter

import android.app.Activity
import android.net.Uri
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.NO_POSITION
import com.bogarsoft.imagepicker.R
import com.bumptech.glide.Glide
import com.bogarsoft.imagepicker.base.BaseRecyclerViewAdapter
import com.bogarsoft.imagepicker.base.BaseViewHolder
import com.bogarsoft.imagepicker.databinding.ItemSelectedMediaBinding

internal class SelectedMediaAdapter :
    BaseRecyclerViewAdapter<Uri, SelectedMediaAdapter.MediaViewHolder>() {

    var onClearClickListener: ((Uri) -> Unit)? = null
    private var layoutManager: RecyclerView.LayoutManager? = null

    override fun getViewHolder(parent: ViewGroup, viewType: ViewType) = MediaViewHolder(parent)

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        layoutManager = recyclerView.layoutManager
    }


    inner class MediaViewHolder(parent: ViewGroup) :
        BaseViewHolder<ItemSelectedMediaBinding, Uri>(parent, R.layout.item_selected_media) {

        init {
            binding.ivClear.setOnClickListener {
                val item = getItem(adapterPosition.takeIf { it != NO_POSITION }
                    ?: return@setOnClickListener)
                onClearClickListener?.invoke(item)
            }
        }

        override fun bind(data: Uri) {
            binding.uri = data
        }

        override fun recycled() {
            if ((itemView.context as? Activity)?.isDestroyed == true) {
                return
            }
            Glide.with(itemView).clear(binding.ivImage)
        }
    }

}