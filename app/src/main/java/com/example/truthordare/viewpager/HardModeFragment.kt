package com.example.truthordare.viewpager

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.truthordare.databinding.FragmentHardModeBinding
import com.example.truthordare.game.ChoosePlayerActivity
import com.example.truthordare.interfaces.PlayerNameListener
import com.example.truthordare.model.GameMode
import com.example.truthordare.model.PlayerData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class HardModeFragment : Fragment(), PlayerNameListener {

    private var _binding: FragmentHardModeBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHardModeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.hardModePlayBtn.setOnClickListener {
            val playerList = retrievePlayers()
            val intent = Intent(activity, ChoosePlayerActivity::class.java).apply {
                putStringArrayListExtra("playerNames", playerList)
                putExtra("gameMode", GameMode.HARD.name) // Передаем режим
            }
            startActivity(intent)
        }

        return root
    }

    private fun retrievePlayers(): ArrayList<String> {
        val sharedPrefs = requireContext().getSharedPreferences("PlayerName", Context.MODE_PRIVATE)
        val json = sharedPrefs.getString("players", null)
        val gson = Gson()
        val type = object : TypeToken<ArrayList<PlayerData>>() {}.type
        val players: List<PlayerData> = gson.fromJson(json, type) ?: return arrayListOf()


        return ArrayList(players.map { it.playerName })
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onPlayerDataPass(playerNames: ArrayList<String>) {
        // Сохранение или дальнейшая обработка имен игроков, если необходимо
    }

}