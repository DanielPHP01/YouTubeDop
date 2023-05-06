package com.example.youtubedop.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.youtubedop.BuildConfig
import com.example.youtubedop.base.BaseViewModel
import com.example.youtubedop.model.Playlist
import com.example.youtubedop.remote.ApiService
import com.example.youtubedop.remote.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PlaylistsViewModel: BaseViewModel() {

    private val apiService: ApiService by lazy {
        RetrofitClient.create()
    }

    fun playlists() : LiveData<Playlist> {
        return getPlaylists()
    }

    private fun getPlaylists() : LiveData<Playlist> {
        val data = MutableLiveData<Playlist>()

        apiService.getPlaylists(BuildConfig.API_KEY, "contentDetails,snippet", "UC9Rc75wIi4wh5Ir4Th8Di_g",30)
            .enqueue(object : Callback<Playlist> {
                override fun onResponse(call: Call<Playlist>, response: Response<Playlist>) {
                    if (response.isSuccessful){
                        data.value = response.body()
                    }
                }

                override fun onFailure(call: Call<Playlist>, t: Throwable) {
                    print(t.stackTrace)
                }
            })

        return data
    }
}