package com.example.musicplayer.controllers

import com.example.musicplayer.R
import com.example.musicplayer.databinding.ActivityMusicDetailsBinding
import com.example.musicplayer.media.MediaPlayer
import com.example.musicplayer.media.Song

class MediaControllers {

        fun pauseMusic() {
       MediaPlayer.mediaPlayer?.let {
           if (MediaPlayer.mediaPlayer!!.isPlaying) {
               MediaPlayer.mediaPlayer!!.pause()
           }
       }
   }

       fun playMusic(){
           MediaPlayer.mediaPlayer?.let {
               if (!MediaPlayer.mediaPlayer!!.isPlaying){
                   MediaPlayer.mediaPlayer!!.start()
               }
           }

    }

}