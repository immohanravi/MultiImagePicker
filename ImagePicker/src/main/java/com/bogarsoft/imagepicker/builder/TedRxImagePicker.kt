package com.bogarsoft.imagepicker.builder

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import com.bogarsoft.imagepicker.builder.listener.OnErrorListener
import com.bogarsoft.imagepicker.builder.listener.OnMultiSelectedListener
import com.bogarsoft.imagepicker.builder.listener.OnSelectedListener
import com.bogarsoft.imagepicker.builder.type.SelectType
import io.reactivex.Single
import io.reactivex.SingleEmitter
import java.lang.ref.WeakReference


class TedRxImagePicker {
    companion object {
        @JvmStatic
        fun with(context: Context) = Builder(WeakReference(context))
    }

    @SuppressLint("ParcelCreator")
    class Builder(private val contextWeakReference: WeakReference<Context>) :
        TedImagePickerBaseBuilder<Builder>() {

        fun start(): Single<Uri> =
            Single.create { emitter ->
                this.onSelectedListener = object : OnSelectedListener {
                    override fun onSelected(uri: Uri) {
                        emitter.onSuccess(uri)
                    }
                }
                start(SelectType.SINGLE, emitter)
            }


        fun startMultiImage(): Single<List<Uri>> =
            Single.create { emitter ->
                this.onMultiSelectedListener = object : OnMultiSelectedListener {
                    override fun onSelected(uriList: List<Uri>) {
                        emitter.onSuccess(uriList)
                    }
                }
                start(SelectType.MULTI, emitter)
            }

        private fun start(selectType: SelectType, emitter: SingleEmitter<*>) {
            this.onErrorListener = object : OnErrorListener {
                override fun onError(throwable: Throwable) {
                    emitter.onError(throwable)
                }
            }
            this.selectType = selectType
            contextWeakReference.get()?.let {
                startInternal(it)
            }

        }
    }


}


