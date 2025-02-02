package com.example.truthordare

import android.os.Bundle
import android.service.autofill.UserData
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.truthordare.adapter.PlayersAdapter
import com.example.truthordare.databinding.FragmentPlayersBinding
import com.example.truthordare.model.PlayerData


class PlayersFragment : Fragment(), AddPlayerFragment.PlayerListener {

    private var _binding: FragmentPlayersBinding? = null
    private val binding get() = _binding!!
    private lateinit var playerList: ArrayList<PlayerData>
    private lateinit var playersAdapter: PlayersAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPlayersBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.backBtn.setOnClickListener {
            findNavController().navigate(R.id.action_playersFragment_to_versionsFragment)
        }

        playerList = ArrayList()

        playerList.add(PlayerData("Игрок 1"))
        playerList.add(PlayerData("Игрок 2"))
        playerList.add(PlayerData("Игрок 3"))

        playersAdapter = PlayersAdapter(requireContext(), playerList)
        binding.rvPlayers.layoutManager = LinearLayoutManager(requireContext()) // Укажите LayoutManager
        binding.rvPlayers.adapter = playersAdapter

        binding.addPlayerBtn.setOnClickListener {
            // Создаем новый фрагмент для добавления игроков
            val addPlayerFragment = AddPlayerFragment()
            addPlayerFragment.setPlayerListener(this) // Установите слушателя
            addPlayerFragment.show(parentFragmentManager, addPlayerFragment.tag)
        }

        return root
    }

    override fun onPlayerAdded(playerName: String) {
        Log.d("PlayersFragment", "Player added: $playerName")
        playerList.add(PlayerData(playerName))
        playersAdapter.notifyItemInserted(playerList.size - 1)
        playersAdapter.notifyDataSetChanged() // это можно оставить, если вы сделали изменения
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}