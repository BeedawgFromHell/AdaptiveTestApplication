package com.example.adaptivetestapplication

import android.content.Intent
import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.adaptivetestapplication.data.ImagesService
import com.example.adaptivetestapplication.databinding.MainActivityBinding
import com.example.adaptivetestapplication.tools.UiMetrics
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    companion object {
        const val BASE_URL = "https://dev-tasks.alef.im/"
    }

    private lateinit var binding: MainActivityBinding
    private val api: ImagesService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ImagesService::class.java)
    }
    private var adapter: ImagesAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setRv()
        setImages()

        binding.swipeLayout.setOnRefreshListener {
            adapter?.submitList(listOf())
            setImages()
        }
    }

    private fun setRv() {
        adapter = ImagesAdapter()
        adapter?.clickListener = {
            val intent = Intent(this, ImageFullScreenActivity::class.java)
            intent.putExtra(ImageFullScreenActivity.IMAGE_URL, it)
            startActivity(intent)
        }

        binding.imagesRv.apply {
            val width = UiMetrics.getCurrentWidth(this@MainActivity)
            layoutManager = when {
                width > 600F -> {
                    GridLayoutManager(this@MainActivity, 3)
                }
                else -> {
                    GridLayoutManager(this@MainActivity, 2)
                }
            }
            adapter = this@MainActivity.adapter
        }
    }

    private fun setImages() {
        lifecycleScope.launchWhenCreated {
            api.getImages().body()?.let { images ->
                adapter?.submitList(images)
                binding.swipeLayout.isRefreshing = false
            }
        }

    }

}