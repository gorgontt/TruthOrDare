package com.example.truthordare.game

import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Toast
import android.widget.VideoView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.truthordare.R

class ChoosePlayerActivity : AppCompatActivity() {

    private lateinit var videoView: VideoView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_choose_player)

        videoView = findViewById(R.id.gif)

        val videoUri = Uri.parse("android.resource://" + packageName + "/" + R.raw.cat_video)
        videoView.setVideoURI(videoUri)
        videoView.start()

        Handler(Looper.getMainLooper()).postDelayed({
            videoView.visibility = View.GONE

            chooseRandomPlayer()
        }, 2000)
    }

    private fun chooseRandomPlayer() {
        val playerNames = intent.getStringArrayListExtra("playerNames") ?: return
        if (playerNames.isNotEmpty()) {
            val randomPlayer = playerNames.random()
            Toast.makeText(this, "Случайный игрок: $randomPlayer", Toast.LENGTH_LONG).show()
            // Здесь вы можете продолжить с выбранным игроком (например, начать игру)
        } else {
            Toast.makeText(this, "Не найдено игроков", Toast.LENGTH_SHORT).show()
        }
    }
}