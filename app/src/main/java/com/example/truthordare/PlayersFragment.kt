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


class PlayersFragment : Fragment() {

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

//        binding.addPlayerBtn.setOnClickListener {
//            val addPlayerFragment = AddPlayerFragment()
//            addPlayerFragment.setPlayerListener(this) // Установите слушателя
//            addPlayerFragment.show(parentFragmentManager, addPlayerFragment.tag)
//        }




        playerList = ArrayList()

        playersAdapter = PlayersAdapter(requireContext(), playerList)
        binding.rvPlayers.layoutManager = LinearLayoutManager(requireContext()) // Укажите LayoutManager
        binding.rvPlayers.adapter = playersAdapter

        binding.addPlayerBtn.setOnClickListener { addInfo() }

        return root
    }

    private fun addInfo(){
        val inflater = LayoutInflater.from(requireContext())
        val v = inflater.inflate(R.layout.alert_dialog, null)
        val playerName = v.findViewById<EditText>(R.id.player_name_edT_alertDialog)
        val addBtn = v.findViewById<TextView>(R.id.add_new_player_btn_alerDialog)
        val addDialog = AlertDialog.Builder(requireContext())

        addDialog.setPositiveButton("OK"){
            dialog,_->
            val names = playerName.text.toString()
            playerList.add(PlayerData(names))
            playersAdapter.notifyDataSetChanged()
            dialog.dismiss()
        }

        addBtn.setOnClickListener {dialog->

        }


        addDialog.setView(v)
        addDialog.create()
        addDialog.show()
    }

//    override fun onPlayerAdded(playerName: String) {
//        Log.e("PlayersFragment", "Player added: $playerName")
//        playerList.add(PlayerData(playerName))
//        playersAdapter.notifyItemInserted(playerList.size - 1)
//    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}