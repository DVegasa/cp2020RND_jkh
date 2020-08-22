package io.github.dvegasa.cp2020rnd.features.voting_screen

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import com.poovam.pinedittextfield.PinField
import io.github.dvegasa.cp2020rnd.R
import kotlinx.android.synthetic.main.fragment_voting.*
import kotlinx.android.synthetic.main.fragment_voting.view.*
import kotlinx.android.synthetic.main.rvitem_voting.view.*


class VotingFragment : Fragment() {

    private var votingList: List<VotingModel>? = null
    private lateinit var view_: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        view_ = inflater.inflate(R.layout.fragment_voting, container, false)

        votingList = getVotingList()
        updateVotingsUI()

        view_.apply {

            btnSmsRequest.setOnClickListener {
                btnSmsRequest.visibility = View.GONE
                pinField.visibility = View.VISIBLE
                tvInfo.text = "Введите код из СМС-сообщения\nВ демо-версии можно ввести любые цифры"

                pinField.requestFocus()
                val imm: InputMethodManager? =
                    context!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm!!.showSoftInput(pinField, InputMethodManager.SHOW_IMPLICIT)
            }

            pinField.onTextCompleteListener = object : PinField.OnTextCompleteListener {
                override fun onTextComplete(enteredText: String): Boolean {
                    pinCodeEntered()
                    return true
                }
            }

            llBack.setOnClickListener {
                this@VotingFragment.requireFragmentManager().popBackStack()
            }
        }
        return view_
    }

    private fun getVotingList(): List<VotingModel> {
        return listOf(
            VotingModel(1L, "Установка футбольных ворот", "", listOf()),
            VotingModel(2L, "Покраска стен в красный цвет", "", listOf()),
            VotingModel(3L, "Озеленение парковочных мест", "", listOf()),
            VotingModel(4L, "Выборы УК", "", listOf())
        )
    }

    private fun pinCodeEntered() {
        view_.pinField.visibility = View.GONE
        view_.tvInfo.text = "Теперь вы можете принять участие в голосовании"
        for (v in this.votingList!!) {
            v.isLocked = false
        }
        updateVotingsUI()
    }

    private fun updateVotingsUI() {
        view_.llVotings.removeAllViews()

        val list = this.votingList!!

        for (pos in list.indices) {
            val view = LayoutInflater.from(context).inflate(R.layout.rvitem_voting, view_.llVotings, false)
            view.apply {
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
            view_.llVotings.addView(view)
        }
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