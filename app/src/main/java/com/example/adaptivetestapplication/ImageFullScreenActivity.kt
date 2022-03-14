package com.example.adaptivetestapplication

import android.os.Bundle
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.Glide

class ImageFullScreenActivity : AppCompatActivity() {
    companion object {
        const val IMAGE_URL = "image_url"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val url = intent.getStringExtra(IMAGE_URL)
        val imageView = AppCompatImageView(this).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
        }

        setContentView(imageView)

        Glide.with(imageView)
            .load(url)
            .into(imageView)
    }
}