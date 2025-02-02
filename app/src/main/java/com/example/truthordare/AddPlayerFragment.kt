package com.example.truthordare

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class AddPlayerFragment : BottomSheetDialogFragment() {

//    // Интерфейс для передачи имени игрока
    interface PlayerListener {
        fun onPlayerAdded(playerName: String)
    }
    fun setPlayerListener(listener: PlayerListener) {
        playerListener = listener
    }


    private var playerListener: PlayerListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        // Установим listener
        playerListener = context as? PlayerListener
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_player, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val behavior = BottomSheetBehavior.from(view.parent as View)
        behavior.peekHeight = BottomSheetBehavior.PEEK_HEIGHT_AUTO
        behavior.state = BottomSheetBehavior.STATE_EXPANDED

        val playerNameEditText = view.findViewById<EditText>(R.id.player_name_edT)
        val addNewPlayerButton = view.findViewById<TextView>(R.id.add_new_player_btn)

        addNewPlayerButton.setOnClickListener {
            val playerName = playerNameEditText.text.toString().trim()
            if (playerName.isNotEmpty()) {
                playerListener?.onPlayerAdded(playerName)
                dismiss()
            } else {
                Toast.makeText(context, "Введите имя игрока", Toast.LENGTH_SHORT).show()
            }
        }
    }
}