package com.example.musicplayer.media

import java.io.Serializable

data class Song(val name:String, val resource:Int, val image: Int) : Serializable
