package com.bogarsoft.multiimagepicker

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import com.bogarsoft.imagepicker.builder.TedImagePicker

class MainActivity : AppCompatActivity() {

    private var selectedUriList: List<Uri>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

            Handler(Looper.getMainLooper()).postDelayed({
                    TedImagePicker.with(this)
                        .max(5,"Not More that 5 Pictures")
                        .min(5,"Select All 5 Images")
                        .errorListener { message -> Log.d("ted", "message: $message") }
                        .cancelListener { Log.d("ted", "image select cancel") }
                        .selectedUri(selectedUriList)
                        .zoomIndicator(true)
                        .startMultiImage { list: List<Uri> -> Log.d(TAG, "onCreate: $list") }
            },2000)
    }
    
    companion object{
        private const val TAG = "MainActivity"
    }
}