package com.example.truthordare

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.drawable.ShapeDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.truthordare.adapter.PlayersAdapter
import com.example.truthordare.databinding.FragmentPlayersBinding
import com.example.truthordare.model.PlayerData
import com.example.truthordare.viewpager.ViewPagerActivity
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class PlayersFragment : Fragment() {

    private var _binding: FragmentPlayersBinding? = null
    private val binding get() = _binding!!
    private lateinit var playerList: ArrayList<PlayerData>
    private lateinit var playersAdapter: PlayersAdapter
    private lateinit var sharedPrefs: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPlayersBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.backBtn.setOnClickListener {
            findNavController().navigate(R.id.action_playersFragment_to_versionsFragment)
        }

        binding.startBtn.setOnClickListener {
            val intent = Intent(activity, ViewPagerActivity::class.java)
            startActivity(intent)
        }

        sharedPrefs = requireContext().getSharedPreferences("PlayerName", Context.MODE_PRIVATE)

        playerList = loadPlayers() // Загружаем игроков из SharedPreferences

        playersAdapter = PlayersAdapter(requireContext(), playerList)
        binding.rvPlayers.layoutManager = LinearLayoutManager(requireContext())
        binding.rvPlayers.adapter = playersAdapter

        binding.addPlayerBtn.setOnClickListener { addInfo() }



        return root
    }

    private fun addInfo() {
        val inflater = LayoutInflater.from(requireContext())
        val v = inflater.inflate(R.layout.alert_dialog, null)

        val playerName = v.findViewById<EditText>(R.id.player_name_edT_alertDialog)
        val addBtn = v.findViewById<TextView>(R.id.add_new_player_btn_alerDialog)

        val bottomSheetDialog = BottomSheetDialog(requireContext())
        bottomSheetDialog.setContentView(v)

        addBtn.setOnClickListener {
            val names = playerName.text.toString()

            if (names.isNotBlank()) {
                playerList.add(PlayerData(names))
                playersAdapter.notifyDataSetChanged()
                savePlayers() // Сохраняем игроков в SharedPreferences
                bottomSheetDialog.dismiss()
            } else {
                Toast.makeText(requireContext(), "Введите имя игрока", Toast.LENGTH_SHORT).show()
            }
        }

        bottomSheetDialog.show()
    }

    private fun savePlayers() {
        val editor = sharedPrefs.edit()
        val gson = Gson()
        val json = gson.toJson(playerList) // Преобразуем список в JSON
        editor.putString("players", json)
        editor.apply()
    }

    private fun loadPlayers(): ArrayList<PlayerData> {
        val json = sharedPrefs.getString("players", null) ?: return arrayListOf()
        val gson = Gson()
        val type = object : TypeToken<ArrayList<PlayerData>>() {}.type
        return gson.fromJson(json, type) // Преобразуем JSON обратно в список
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}