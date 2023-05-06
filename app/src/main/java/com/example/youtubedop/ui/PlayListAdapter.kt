package com.example.youtubedop.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.youtubedop.databinding.ItemPlaylistBinding
import com.example.youtubedop.model.Playlist
import com.example.youtubedop.viewext.loadImage


class PlayListAdapter(private val onClick: (Playlist.Item) -> Unit) :
    RecyclerView.Adapter<PlayListAdapter.PlayListViewHolder>() {
    private var list = arrayListOf<Playlist.Item>()

    @SuppressLint("NotifyDataSetChanged")
    fun addList(list : List<Playlist.Item>) {
        this.list = list as ArrayList<Playlist.Item>
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayListViewHolder {
        return PlayListViewHolder(
            ItemPlaylistBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: PlayListViewHolder, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class PlayListViewHolder(private val binding: ItemPlaylistBinding) :
        ViewHolder(binding.root) {
        fun onBind(item:Playlist.Item) {
            with(binding) {
                tvPlaylistName.text = item.snippet?.title
                tvCountVideo.text = item.contentDetails?.itemCount.toString() + "videos series"
                ivPlaylist.loadImage(item.snippet?.thumbnails?.standard?.url!!)
                cvPlaylist.setOnClickListener {
                    onClick.invoke(item)
                }
            }
        }
    }
}