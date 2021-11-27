package com.ilhmdhn.gamecatalog.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ilhmdhn.gamecatalog.R
import com.ilhmdhn.gamecatalog.core.data.Resource
import com.ilhmdhn.gamecatalog.databinding.ActivityMainBinding
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val homeViewModel: HomeViewModel by viewModel()

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        homeViewModel.game.observe(this, {game ->
            if (game != null){
                when (game){
                    is Resource.Loading -> binding?.tv?.text = "loading"
                    is Resource.Success -> binding?.tv?.text = "${game.data}"
                    is Resource.Error -> binding?.tv?.text = "${game.message}"
                }
            }
        })
    }
}