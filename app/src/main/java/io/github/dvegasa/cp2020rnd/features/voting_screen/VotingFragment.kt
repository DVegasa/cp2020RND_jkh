package io.github.dvegasa.cp2020rnd.features.voting_screen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.github.dvegasa.cp2020rnd.R

class VotingFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_voting, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            VotingFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}