package io.github.dvegasa.cp2020rnd.features.voting_screen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.github.dvegasa.cp2020rnd.R
import kotlinx.android.synthetic.main.rvitem_voting.view.*

/**
 * Created by Ed Khalturin @DVegasa
 */
class RvVotingsAdapter(val list: List<VotingModel>) : RecyclerView.Adapter<RvVotingsAdapter.VH>() {

    inner class VH(private val v: View) : RecyclerView.ViewHolder(v) {
        fun bind(pos: Int) {
            v.apply {
                tvOrderNo.text = (pos + 1).toString()
                tvTitle.text = list[pos].title
                ivIcon.setImageResource(
                    when {
                        list[pos].isLocked -> R.drawable.ic_baseline_info_24
                        list[pos].answer == Answers.NOT_ANSWERED -> R.drawable.ic_baseline_send_24
                        else -> R.drawable.ic_baseline_edit_24
                    }
                )
                if (list[pos].answer == Answers.NOT_ANSWERED) {
                    tvAnswer.visibility = View.GONE
                } else {
                    tvAnswer.visibility = View.VISIBLE
                    tvAnswer.text = when (list[pos].answer) {
                        Answers.NO -> "Голос ПРОТИВ"
                        Answers.YES -> "Голос ЗА"
                        Answers.REFRAIN -> "Воздерживаюсь"
                        else -> ""
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rvitem_voting, parent, false)

        return VH(view)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(position)
    }
}