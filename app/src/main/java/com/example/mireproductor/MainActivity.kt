package com.example.mireproductor

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.android.material.button.MaterialButton

class MainActivity : AppCompatActivity() {

    val fd by lazy {
        assets.openFd("abre-los-cielos-jesus-adrian-romero-youtube.mp3")
    }

        val mp by lazy{
            val m = MediaPlayer()
            m.setDataSource(
                fd.fileDescriptor,
                fd.startOffset,
                fd.length
            )
            fd.close()
            m.prepare()
            m
        }
        val controllers by lazy {
            listOf(R.id.next,R.id.stop,R.id.play,R.id.prev).map {
                findViewById<MaterialButton>(it)
            }
        }
        object ci{
            val next = 0
            val stop = 1
            val play = 2
            val prev = 3
        }
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)
            controllers[ci.play].setOnClickListener(this::playOnclicked)
            controllers[ci.stop].setOnClickListener(this::stopOnclicked)

        }

        fun playOnclicked(v: View){
            if(!mp.isPlaying) {
                mp.start()
            }else{
                mp.pause()
            }

        }
        fun stopOnclicked(v: View){
            if (mp.isPlaying){
                mp.pause()
            }
            mp.seekTo(0)
        }
}