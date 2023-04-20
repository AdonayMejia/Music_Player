package com.example.musicplayer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import com.example.musicplayer.controllers.MediaControllers
import com.example.musicplayer.databinding.ActivityMusicDetailsBinding
import com.example.musicplayer.media.MediaPlayer

class MusicDetails : AppCompatActivity() {

    private lateinit var binding: ActivityMusicDetailsBinding
    private var controller = MediaControllers()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMusicDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var playButton = binding.play
        //var seekBar = binding.seekBar
        val name = intent.getStringExtra("name")

        binding.songName.text = name
        //seekBar.progress = 0
        //seekBar.max = MediaPlayer.mediaPlayer?.duration!!

        playButton.setOnClickListener{
            MediaPlayer.mediaPlayer?.let {
                if (MediaPlayer.mediaPlayer!!.isPlaying){
                    controller.pauseMusic()
                    playButton.setImageResource(R.drawable.play)
                } else{
                    controller.playMusic()
                    playButton.setImageResource(R.drawable.pause)
                }
            }
        }


        /*seekBar.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{

            override fun onProgressChanged(seekBar: SeekBar?, pos: Int, changed: Boolean) {
                if (changed){
                    MediaPlayer.mediaPlayer!!.seekTo(pos)
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }
        } )*/
    }


}