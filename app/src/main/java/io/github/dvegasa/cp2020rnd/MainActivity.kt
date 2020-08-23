package io.github.dvegasa.cp2020rnd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import io.github.dvegasa.cp2020rnd.features.main_screen.MainFragment
import io.github.dvegasa.cp2020rnd.features.voting_screen.VotingFragment
import kotlinx.android.synthetic.main.activity_main.*



class MainActivity : AppCompatActivity() {

    val mainFrag = MainFragment.newInstance()
    lateinit var votingFrag: VotingFragment


    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadContent()
        loadBottomNav()
    }

    private fun loadBottomNav() {
        val view = LayoutInflater.from(this).inflate(R.layout.view_bottomnav_0, flBottomnavHolder, false)
        flBottomnavHolder.removeAllViews()
        flBottomnavHolder.addView(view)
    }

    private fun loadContent() {
        flFragmentHolder.removeAllViews()
        supportFragmentManager.beginTransaction().apply {
            add(flFragmentHolder.id, mainFrag)
            commit()
        }
    }

    fun showVotingScreen() {
        votingFrag = VotingFragment.newInstance()
        supportFragmentManager.beginTransaction().apply {
            add(android.R.id.content, votingFrag)
            addToBackStack(null)
            commit()
        }
    }
}