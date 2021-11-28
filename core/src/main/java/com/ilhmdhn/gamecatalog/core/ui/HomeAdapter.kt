package com.ilhmdhn.gamecatalog.core.ui

import GameListResult
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ilhmdhn.gamecatalog.core.R
import com.ilhmdhn.gamecatalog.core.databinding.ListGameBinding
import com.ilhmdhn.gamecatalog.core.domain.model.GameListModel

class HomeAdapter: RecyclerView.Adapter<HomeAdapter.ListViewHolder>() {

    private var listData = ArrayList<GameListModel>()
    var onItemClick: ((GameListModel) -> Unit)? = null

    fun setData(newListData: List<GameListModel>?){
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeAdapter.ListViewHolder {
        return ListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_game, parent, false))
    }

    override fun onBindViewHolder(holder: HomeAdapter.ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    inner class ListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)  {
        private val binding = ListGameBinding.bind(itemView)
        fun bind(data: GameListModel){
            with(binding){
                Glide.with(itemView.context)
                    .load(data.backgroundImage)
                    .placeholder(R.drawable.ic_image_loading)
                    .into(gameImage)
                tvGameName.text = data.name
                gameRating.rating = data.rating?.toFloat() ?: 0f
            }
        }

        init{
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }

    override fun getItemCount(): Int = listData.size
}