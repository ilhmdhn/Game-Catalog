package com.ilhmdhn.gamecatalog.core.ui

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import androidx.recyclerview.widget.RecyclerView
import com.ilhmdhn.gamecatalog.core.R
import com.ilhmdhn.gamecatalog.core.databinding.VideoGameBinding
import com.ilhmdhn.gamecatalog.core.domain.model.GameMovieModel

class VideoPagerAdapter: RecyclerView.Adapter<VideoPagerAdapter.ListViewHolder>() {

    private var listData = ArrayList<GameMovieModel>()

    fun setData(newListData: List<GameMovieModel>?){
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    inner class ListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val binding = VideoGameBinding.bind(itemView)
        fun bind(data: GameMovieModel){
            val mediaController = MediaController(itemView.context)
            mediaController.show(5000)
            with(binding){
                vvGame.setMediaController(mediaController)
                vvGame.setVideoURI(Uri.parse(data.movie))
                vvGame.requestFocus()
                vvGame.seekTo(1)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoPagerAdapter.ListViewHolder {
        return ListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.video_game, parent, false))
    }

    override fun onBindViewHolder(holder: VideoPagerAdapter.ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int = listData.size
}