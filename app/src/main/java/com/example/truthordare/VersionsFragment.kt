package com.example.truthordare

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.truthordare.databinding.FragmentVersionsBinding


class VersionsFragment : Fragment() {

    private var _binding: FragmentVersionsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentVersionsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.coupleVersion.setOnClickListener {
            findNavController().navigate(R.id.action_versionsFragment_to_playersFragment)
        }

        binding.companyVersion.setOnClickListener {
            findNavController().navigate(R.id.action_versionsFragment_to_playersFragment)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}