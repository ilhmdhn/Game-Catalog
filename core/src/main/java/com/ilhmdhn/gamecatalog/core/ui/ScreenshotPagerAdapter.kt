package com.ilhmdhn.gamecatalog.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ilhmdhn.gamecatalog.core.R
import com.ilhmdhn.gamecatalog.core.databinding.ScreenshotGameBinding
import com.ilhmdhn.gamecatalog.core.domain.model.GameScreenshotModel
import com.ilhmdhn.imageslider.model.ResultsImageItem

class ScreenshotPagerAdapter: RecyclerView.Adapter<ScreenshotPagerAdapter.ListViewHolder>() {

    private var listData = ArrayList<GameScreenshotModel>()

    fun setData(newListData: List<GameScreenshotModel>?){
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    inner class ListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val binding = ScreenshotGameBinding.bind(itemView)
        fun bind(data: GameScreenshotModel){
            with(binding){
                Glide.with(itemView.context)
                    .load(data.image)
                    .into(ivScreenshot)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        return ListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.screenshot_game, parent, false))
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int = listData.size
}