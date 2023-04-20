package com.example.musicplayer

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.view.forEach
import com.example.musicplayer.databinding.ActivityMainBinding
import com.example.musicplayer.media.MediaPlayer
import com.example.musicplayer.media.Song

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    val music = arrayListOf(
        Song(song1, R.raw.song1, R.drawable.love),
        Song(song2, R.raw.song2, R.drawable.ibiza),
        Song(song3, R.raw.song3, R.drawable.alok)
    )
    private var currentSongIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val listItem = binding.listView

        val myListAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, music.map {
            it.name
        })
        listItem.adapter = myListAdapter

        listItem.setOnItemClickListener { parent, view, position, id ->
            play(position)
            goToDetails(position)
        }
    }

    private fun goToDetails(position: Int){
        val intent = Intent(this, MusicDetails::class.java)
        intent.putExtra("name", music[position].name)
        startActivity(intent)

    }

    private fun play(position: Int) {
        MediaPlayer.mediaPlayer?.release()
        currentSongIndex = position
        MediaPlayer.mediaPlayer = android.media.MediaPlayer
            .create(this@MainActivity, music[position].resource)
        MediaPlayer.mediaPlayer?.start()
    }

    companion object {
        const val song1: String = "Let Me Blow Ya Love"
        const val song2: String = "I Took A Pill In Ibiza"
        const val song3: String = "Hear Me Now"
    }


}