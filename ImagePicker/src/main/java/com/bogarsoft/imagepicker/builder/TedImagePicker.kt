package com.bogarsoft.imagepicker.builder

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import com.bogarsoft.imagepicker.builder.listener.OnErrorListener
import com.bogarsoft.imagepicker.builder.listener.ImageSelectCancelListener
import com.bogarsoft.imagepicker.builder.listener.OnMultiSelectedListener
import com.bogarsoft.imagepicker.builder.listener.OnSelectedListener
import com.bogarsoft.imagepicker.builder.type.SelectType
import java.lang.ref.WeakReference


class TedImagePicker {
    companion object {
        @JvmStatic
        fun with(context: Context) = Builder(WeakReference(context))
    }

    @SuppressLint("ParcelCreator")
    class Builder(private val contextWeakReference: WeakReference<Context>) :
        TedImagePickerBaseBuilder<Builder>() {


        fun errorListener(onErrorListener: OnErrorListener): Builder {
            this.onErrorListener = onErrorListener
            return this
        }

        fun errorListener(action: (Throwable) -> Unit): Builder {
            this.onErrorListener = object : OnErrorListener {
                override fun onError(throwable: Throwable) {
                    action(throwable)
                }
            }
            return this
        }

        fun cancelListener(imageSelectCancelListener: ImageSelectCancelListener): Builder {
            this.imageSelectCancelListener = imageSelectCancelListener
            return this
        }

        fun cancelListener(action: () -> Unit): Builder =
            cancelListener(object : ImageSelectCancelListener {
                override fun onImageSelectCancel() {
                    action.invoke()
                }
            })


        fun start(onSelectedListener: OnSelectedListener) {
            this.onSelectedListener = onSelectedListener
            selectType = SelectType.SINGLE
            contextWeakReference.get()?.let {
                startInternal(it)
            }

        }

        fun start(action: (Uri) -> Unit) {
            start(object : OnSelectedListener {
                override fun onSelected(uri: Uri) {
                    action(uri)
                }
            })
        }

        fun startMultiImage(action: (List<Uri>) -> Unit) {
            startMultiImage(object : OnMultiSelectedListener {
                override fun onSelected(uriList: List<Uri>) {
                    action(uriList)
                }
            })
        }

        fun startMultiImage(onMultiSelectedListener: OnMultiSelectedListener) {
            this.onMultiSelectedListener = onMultiSelectedListener
            selectType = SelectType.MULTI
            contextWeakReference.get()?.let {
                startInternal(it)
            }
        }

    }


}


