package io.github.dvegasa.cp2020rnd.features.voting_screen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.github.dvegasa.cp2020rnd.R

/**
 * Created by Ed Khalturin @DVegasa
 */
class RvVotingsAdapter(val list: List<VotingModel>) : RecyclerView.Adapter<RvVotingsAdapter.VH>() {

    class VH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(pos: Int) {

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rvitem_voting, parent, true)

        return VH(view)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(position)
    }
}