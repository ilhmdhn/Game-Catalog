package com.ilhmdhn.gamecatalog.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.ilhmdhn.gamecatalog.R
import com.ilhmdhn.gamecatalog.core.data.Resource
import com.ilhmdhn.gamecatalog.core.domain.model.GameDetailModel
import com.ilhmdhn.gamecatalog.core.ui.ScreenshotPagerAdapter
import com.ilhmdhn.gamecatalog.core.ui.VideoPagerAdapter
import com.ilhmdhn.gamecatalog.databinding.ActivityDetailBinding
import com.ilhmdhn.gamecatalog.ui.home.HomeViewModel
import org.koin.android.ext.android.bind
import org.koin.android.viewmodel.ext.android.viewModel


class DetailActivity : AppCompatActivity() {

    private val detailViewModel: DetailViewModel by viewModel()

    private val videoAdapter = VideoPagerAdapter()
    private val screenshotAdapter = ScreenshotPagerAdapter()

    private var _binding: ActivityDetailBinding? = null
    private val binding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        val idGame = intent.getIntExtra(EXTRA_DATA, 0)
        getData(idGame)

        binding?.vpScreenshot?.adapter = screenshotAdapter
        binding?.vpTrailer?.adapter = videoAdapter

        binding?.swipeLayout?.setOnRefreshListener {
            getData(idGame, true)
            binding?.swipeLayout?.isRefreshing = false
        }
    }

    private fun getData(id: Int, reload: Boolean = false){
        detailViewModel.getGameDetail(id, reload).observe(this, {gameDetail ->
            if (gameDetail != null){
                when (gameDetail){
                    is Resource.Loading -> binding?.progressBar?.visibility = View.VISIBLE

                    is Resource.Success -> {
                        binding?.progressBar?.visibility = View.GONE
                        setView(gameDetail.data)
                    }

                    is Resource.Error -> {
                        binding?.progressBar?.visibility = View.GONE
                        Toast.makeText(this, "Error: ${gameDetail.message}", Toast.LENGTH_LONG).show()
                    }
                }
            }
        })

        detailViewModel.getGameScreenshot(id, reload= false).observe(this, {gameSS ->
            if (gameSS != null){
                when (gameSS){
                    is Resource.Loading -> binding?.vpScreenshot?.setBackgroundResource(R.drawable.ic_image_loading)

                    is Resource.Success -> screenshotAdapter.setData(gameSS.data)

                    is Resource.Error -> {
                        binding?.vpScreenshot?.setBackgroundResource(R.drawable.ic_image_loading)
                        Toast.makeText(this, "Screenshot Error: ${gameSS.message}", Toast.LENGTH_LONG).show()
                    }
                }
            }
        })

        detailViewModel.getGameMovie(id, reload = false).observe(this, {gameMovie->
            if (gameMovie != null){
                when(gameMovie){
                    is Resource.Loading -> binding?.vpTrailer?.setBackgroundResource(R.drawable.ic_image_loading)

                    is Resource.Success -> videoAdapter.setData(gameMovie.data)

                    is Resource.Error -> {
                        binding?.vpTrailer?.setBackgroundResource(R.drawable.ic_image_loading)
                        Toast.makeText(this, "Video Error: ${gameMovie.message}", Toast.LENGTH_LONG).show()
                    }
                }
            }
        })
    }

    private fun setView(data: GameDetailModel?){
        binding?.imgGame?.let {
            Glide.with(this)
                .load(data?.backgroundImage)
                .placeholder(R.drawable.ic_image_loading)
                .into(it)
        }

        binding?.tvDetailGameName?.text = data?.name
        binding?.tvGameDescription?.text = data?.description
        binding?.tvOriGameName?.text = data?.nameOriginal
        binding?.tvGameRating?.text = data?.rating.toString()
        binding?.tvGameReleaseDate?.text = data?.released
        binding?.tvGameWebsite?.text = data?.website
    }

    companion object{
        const val EXTRA_DATA = "extra_data"
    }
}