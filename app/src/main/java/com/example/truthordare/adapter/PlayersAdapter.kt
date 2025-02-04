package com.example.truthordare.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.truthordare.R
import com.example.truthordare.model.PlayerData


class PlayersAdapter(val context: Context, val playerList: ArrayList<PlayerData>, val onPlayerRemoved: (Position: Int) -> Unit) : RecyclerView.Adapter<PlayersAdapter.PlayerViewHolder>() {

    inner class PlayerViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val name = view.findViewById<TextView>(R.id.player_name)
        val deleteButton = view.findViewById<ImageView>(R.id.delete_player_btn)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val v = inflater.inflate(R.layout.players_item_list, parent, false)
        return PlayerViewHolder(v)
    }

    override fun getItemCount(): Int {
        return playerList.size
    }

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        val player = playerList[position]
        holder.name.text = player.playerName

        holder.deleteButton.setOnClickListener {
            // Вызываем функцию удаления игрока
            removePlayer(position)
            onPlayerRemoved(position)  // Уведомляем фрагмент об удалении игрока
        }
    }

    fun removePlayer(position: Int) {
        playerList.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, playerList.size)
    }
}

