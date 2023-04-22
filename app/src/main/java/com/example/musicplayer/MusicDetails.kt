package com.example.musicplayer

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.SeekBar
import com.example.musicplayer.controllers.MediaControllers
import com.example.musicplayer.databinding.ActivityMusicDetailsBinding
import com.example.musicplayer.media.MediaPlayer
import com.example.musicplayer.media.Song

class MusicDetails : AppCompatActivity() {

    private lateinit var binding: ActivityMusicDetailsBinding
    private lateinit var runnable: Runnable
    private lateinit var songs: Array<Song>
    private var currentIndex = 0
    private val controller = MediaControllers()
    private val handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMusicDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val songsList = intent.getSerializableExtra("list") as ArrayList<Song>
        songs = songsList.toTypedArray()

        val playButton = binding.play
        val seekBar = binding.seekBar
        val next = binding.next
        val prev = binding.prev

        seekBar.progress = 0
        seekBar.max = MediaPlayer.mediaPlayer?.duration!!
        playButton.setOnClickListener {
            MediaPlayer.mediaPlayer?.let {
                if (MediaPlayer.mediaPlayer!!.isPlaying) {
                    controller.pauseMusic()
                    playButton.setImageResource(R.drawable.play)
                } else {
                    controller.playMusic()
                    playButton.setImageResource(R.drawable.pause)
                }
            }
        }

        next.setOnClickListener {
            if (currentIndex < songsList.size - 1) {
                currentIndex++
            } else {
                currentIndex = 0
            }
            newSong()
            updateSongInfo()
        }

        prev.setOnClickListener {
            if (currentIndex > 0) {
                currentIndex--
            } else {
                currentIndex = songsList.size - 1
            }
            newSong()
            updateSongInfo()
        }


        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {

            override fun onProgressChanged(seekBar: SeekBar?, pos: Int, changed: Boolean) {
                if (changed) {
                    MediaPlayer.mediaPlayer!!.seekTo(pos)
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }
        })

        runnable = Runnable {
            seekBar.progress = MediaPlayer.mediaPlayer!!.currentPosition
            handler.postDelayed(runnable, 1000)
        }
        handler.postDelayed(runnable, 1000)

        MediaPlayer.mediaPlayer!!.setOnCompletionListener {
            playButton.setImageResource(R.drawable.play)
            seekBar.progress = 0
        }
        albumCover()

    }

    private fun albumCover() {
        val songName = intent.getStringExtra("name")
        binding.songName.text = songName
        currentIndex = songs.indexOfFirst { it.name == songName }
        binding.songImage.setImageResource(
            songs.getOrNull(currentIndex)?.image ?: 0
        )
    }

    private fun newSong() {
        MediaPlayer.mediaPlayer?.let {
            if (MediaPlayer.mediaPlayer!!.isPlaying) {
                MediaPlayer.mediaPlayer!!.stop()
            }
            MediaPlayer.mediaPlayer?.reset()
            val music =
                Uri.parse("android.resource://${packageName}/${songs[currentIndex].resource}")
            MediaPlayer.mediaPlayer?.setDataSource(this, music)
            MediaPlayer.mediaPlayer?.prepare()
            MediaPlayer.mediaPlayer?.start()
            binding.seekBar.max = MediaPlayer.mediaPlayer!!.duration
            handler.postDelayed(runnable, 1000)
            binding.play.setImageResource(R.drawable.pause)
        }

    }

    private fun updateSongInfo() {
        val newName = songs.map { it.name }
        val newImage = songs.map { it.image }

        binding.songImage.setImageResource(newImage[currentIndex])
        binding.songName.text = newName[currentIndex]
    }


}