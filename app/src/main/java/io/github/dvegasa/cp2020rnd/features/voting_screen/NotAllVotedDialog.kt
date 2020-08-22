package io.github.dvegasa.cp2020rnd.features.voting_screen

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import io.github.dvegasa.cp2020rnd.R
import kotlinx.android.synthetic.main.dialog_not_all.view.*

/**
 * Created by Ed Khalturin @DVegasa
 */
class NotAllVotedDialog : DialogFragment() {

    private lateinit var v: View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.dialog_not_all, container, false)
        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        v.apply {
            btnSendAsIs.setOnClickListener {
                (parentFragment as VotingFragment).sendAsIs()
                dismiss()
            }

            btnBackAndAnswer.setOnClickListener {
                (parentFragment as VotingFragment).backAndAnswer()
                dismiss()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        val dialog: Dialog? = dialog
        if (dialog != null) {
            dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
    }
}