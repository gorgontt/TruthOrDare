package com.example.truthordare.game

import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.TextView
import android.widget.Toast
import android.widget.VideoView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.truthordare.R
import com.example.truthordare.model.GameMode

class ChoosePlayerActivity : AppCompatActivity() {

    private lateinit var videoView: VideoView
    private lateinit var truthBtn: TextView
    private lateinit var dareBtn: TextView
    //private lateinit var questionList: List<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_choose_player)

        videoView = findViewById(R.id.gif)
        truthBtn = findViewById(R.id.truth_btn)
        truthBtn = findViewById(R.id.dare_btn)

        truthBtn.setOnClickListener {
            val truthOrDareFragment = TruthOrDareFragment()
            supportFragmentManager.beginTransaction()
                .replace(R.id.main_choose_frame, truthOrDareFragment)
                .addToBackStack(null)
                .commit()
        }

        dareBtn.setOnClickListener {

        }

        val videoUri = Uri.parse("android.resource://" + packageName + "/" + R.raw.cat_video)
        videoView.setVideoURI(videoUri)
        videoView.start()

//        val gameModeString = intent.getStringExtra("gameMode")
//        val gameMode = gameModeString?.let { GameMode.valueOf(it) }
//        questionList = gameMode?.questionList ?: emptyList()  // Получаем соответствующий список вопросов

        Handler(Looper.getMainLooper()).postDelayed({
            videoView.visibility = View.GONE

            chooseRandomPlayer()
        }, 2000)
    }

    private fun chooseRandomPlayer() {
        val playerNames = intent.getStringArrayListExtra("playerNames") ?: return
        if (playerNames.isNotEmpty()) {

            val randomPlayer = playerNames.random()
            val name: TextView = findViewById(R.id.player_name_choose_activity)
            name.text = randomPlayer

        } else {
            Toast.makeText(this, "Не найдено игроков", Toast.LENGTH_SHORT).show()
        }
    }

//    private fun chooseRandomPlayer() {
//        val playerNames = intent.getStringArrayListExtra("playerNames") ?: return
//        if (playerNames.isNotEmpty()) {
//            val randomPlayer = playerNames.random()
//
//            if (questionList.isNotEmpty()) {
//                val randomQuestion = questionList.random()
//
//                val name: TextView = findViewById(R.id.random_name)
//                name.text = "$randomPlayer, $randomQuestion"
//            }
//            // Здесь вы можете продолжить с выбранным игроком
//        } else {
//            Toast.makeText(this, "Не найдено игроков", Toast.LENGTH_SHORT).show()
//        }
//
//    }
}