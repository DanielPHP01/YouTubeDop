package com.example.youtubedop.ui

import androidx.lifecycle.ViewModelProvider
import com.example.youtubedop.base.BaseActivity
import com.example.youtubedop.databinding.ActivityPlaylistBinding
import com.example.youtubedop.model.Playlist

class PlaylistActivity : BaseActivity<ActivityPlaylistBinding, PlaylistsViewModel>() {

    private lateinit var adapter: PlayListAdapter

    override fun initViews() {
        super.initViews()
        adapter = PlayListAdapter(this::onClick)
        binding.recyclerView.adapter = adapter
    }

    override fun initViewModel() {
        super.initViewModel()
        viewModel.playlists().observe(this) {
            binding.recyclerView.adapter = adapter
            adapter.addList(it.items!! as List<Playlist.Item>)
        }
    }

    override val viewModel: PlaylistsViewModel by lazy {
        ViewModelProvider(this)[PlaylistsViewModel::class.java]
    }

    override fun inflateViewBinding(): ActivityPlaylistBinding {
        return ActivityPlaylistBinding.inflate(layoutInflater)
    }

    private fun onClick(item:Playlist.Item) {

    }
}