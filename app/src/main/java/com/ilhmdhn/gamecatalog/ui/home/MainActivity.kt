package com.ilhmdhn.gamecatalog.ui.home

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.ilhmdhn.gamecatalog.R
import com.ilhmdhn.gamecatalog.core.data.Resource
import com.ilhmdhn.gamecatalog.core.ui.HomeAdapter
import com.ilhmdhn.gamecatalog.databinding.ActivityMainBinding
import com.ilhmdhn.gamecatalog.ui.detail.DetailActivity
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val homeViewModel: HomeViewModel by viewModel()
    private val homeAdapter = HomeAdapter()

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.swipeLayout?.setOnRefreshListener {
            getGame(reload = true)
            binding?.swipeLayout?.isRefreshing = false
        }

        homeAdapter.onItemClick = {selectedData ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_DATA, selectedData.id)
            startActivity(intent)
        }

        getGame()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu?.findItem(R.id.menu_search)?.actionView as SearchView
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.queryHint = resources.getString(R.string.search_game)
        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query.isNullOrEmpty()) {
                    getGame("")
                }else{
                    getGame(query)
                }
                    return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
        return true
    }

    fun getGame(search: String = "", reload: Boolean = false){
        homeViewModel.getGameList(search, reload).observe(this, {game ->
            if (game != null){
                when (game){
                    is Resource.Loading -> binding?.pbGame?.visibility = View.VISIBLE
                    is Resource.Success -> {
                        binding?.pbGame?.visibility = View.GONE
                        homeAdapter.setData(game.data)
                    }
                    is Resource.Error -> {
                        binding?.pbGame?.visibility = View.GONE
                        Toast.makeText(this, "onFailure: ${game.message}", Toast.LENGTH_LONG).show()
                    }
                }
            }
        })

        with(binding?.rvGame){
            this?.layoutManager = LinearLayoutManager(applicationContext)
            this?.setHasFixedSize(true)
            this?.adapter = homeAdapter
        }
    }
}