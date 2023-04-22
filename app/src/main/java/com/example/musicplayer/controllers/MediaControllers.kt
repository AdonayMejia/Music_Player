package com.example.musicplayer.controllers

import com.example.musicplayer.media.MediaPlayer

class MediaControllers {
    fun pauseMusic() {
        MediaPlayer.mediaPlayer?.let {
            if (MediaPlayer.mediaPlayer!!.isPlaying) {
                MediaPlayer.mediaPlayer!!.pause()
            }
        }
    }

    fun playMusic() {
        MediaPlayer.mediaPlayer?.let {
            if (!MediaPlayer.mediaPlayer!!.isPlaying) {
                MediaPlayer.mediaPlayer!!.start()
            }
        }

    }


}