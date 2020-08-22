package io.github.dvegasa.cp2020rnd.features.voting_screen

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment
import io.github.dvegasa.cp2020rnd.R
import kotlinx.android.synthetic.main.voting_details.view.*


/**
 * Created by Ed Khalturin @DVegasa
 */
class VotingDetailsDialog : DialogFragment() {

    private lateinit var v: View
    private lateinit var voting: VotingModel
    private var pos: Int? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.voting_details, container, false)
        v.apply {
            tvNo.text = pos.toString()
            tvDetailsHeader.text = voting.title
            tvDetails.text = voting.message

            if (voting.isLocked) {
                btnVoteNo.visibility = View.GONE
                btnVoteYes.visibility = View.GONE
                btnVoteRefrain.visibility = View.GONE
                tvCantVote.visibility = View.VISIBLE
                btnConfirmAnswer.visibility = View.GONE
                btnBackFromVoting.visibility = View.VISIBLE
            } else {
                btnVoteNo.visibility = View.VISIBLE
                btnVoteYes.visibility = View.VISIBLE
                btnVoteRefrain.visibility = View.VISIBLE
                tvCantVote.visibility = View.GONE
                btnConfirmAnswer.visibility = View.VISIBLE
                btnBackFromVoting.visibility = View.GONE
            }
        }
        return v
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        voting = arguments!!.getParcelable("voting")!!
        pos = arguments!!.getInt("pos")
    }

    companion object {
        @JvmStatic
        fun newInstance(voting: VotingModel, pos: Int): VotingDetailsDialog {
            val f = VotingDetailsDialog()

            val args = Bundle()
            args.putParcelable("voting", voting)
            args.putInt("pos", pos)
            f.arguments = args
            return f
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog: Dialog = super.onCreateDialog(savedInstanceState)
        dialog.window!!.requestFeature(Window.FEATURE_NO_TITLE)
        return dialog
    }

    override fun onStart() {
        super.onStart()
        val dialog: Dialog? = dialog
        if (dialog != null) {
            dialog.window!!.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
            dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
    }
}