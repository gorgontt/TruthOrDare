package com.example.truthordare.game

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.example.truthordare.R
import com.example.truthordare.databinding.FragmentQuestonsBinding
import com.example.truthordare.databinding.FragmentSoftModeBinding
import com.example.truthordare.model.GameMode

class QuestonsFragment : Fragment() {

    private lateinit var questionList: List<String>

    private var _binding: FragmentQuestonsBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentQuestonsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val gameModeString = intent.getStringExtra("gameMode")
        val gameMode = gameModeString?.let { GameMode.valueOf(it) }
        questionList = gameMode?.questionList ?: emptyList()  // Получаем соответствующий список вопросов

        chooseRandomPlayer()

        return root
    }

    private fun chooseRandomPlayer() {
        val playerNames = intent.getStringArrayListExtra("playerNames") ?: return
        if (playerNames.isNotEmpty()) {
            val randomPlayer = playerNames.random()

            if (questionList.isNotEmpty()) {
                val randomQuestion = questionList.random()

                val name: TextView = findViewById(R.id.random_name)
                name.text = "$randomPlayer, $randomQuestion"
            }
            // Здесь вы можете продолжить с выбранным игроком
        } else {
            Toast.makeText(this, "Не найдено игроков", Toast.LENGTH_SHORT).show()
        }

    }


}