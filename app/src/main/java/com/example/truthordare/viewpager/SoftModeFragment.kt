package com.example.truthordare.viewpager

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.truthordare.databinding.FragmentSoftModeBinding
import com.example.truthordare.game.ChoosePlayerActivity


class SoftModeFragment : Fragment() {

    private var _binding: FragmentSoftModeBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSoftModeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.softModePlayBtn.setOnClickListener {

            val intent = Intent(activity, ChoosePlayerActivity::class.java)
            startActivity(intent)

        }

        return root


    }

}