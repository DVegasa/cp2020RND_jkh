package io.github.dvegasa.cp2020rnd.features.voting_screen

import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.gson.JsonObject
import com.poovam.pinedittextfield.PinField
import io.github.dvegasa.cp2020rnd.R
import io.github.dvegasa.cp2020rnd.network.ApiNikita
import io.github.dvegasa.cp2020rnd.network.BallotVote
import io.github.dvegasa.cp2020rnd.network.RetrofitGenerator
import io.github.dvegasa.cp2020rnd.storage.Votings
import kotlinx.android.synthetic.main.fragment_voting.*
import kotlinx.android.synthetic.main.fragment_voting.view.*
import kotlinx.android.synthetic.main.rvitem_voting.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class VotingFragment : Fragment() {

    private var votingList: List<VotingModel>? = null
    private lateinit var view_: View
    private var notAllWasShown = false


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

            btnSendResults.setOnClickListener ClickListener@{
                for (i in votingList!!.indices) {
                    if (votingList!![i].answer == Answers.NOT_ANSWERED && !notAllWasShown) {
                        notAllWasShown = true
                        NotAllVotedDialog().show(childFragmentManager, null)
                        return@ClickListener
                    }
                }
                sendAsIs()
            }

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
                this@VotingFragment.requireFragmentManager().popBackStackImmediate()
            }
        }
        return view_
    }

    fun sendAsIs() {
        btnSendResults.visibility = View.GONE
        progressBar.visibility = View.VISIBLE
        publishResults()
    }

    fun backAndAnswer() {
        notAllWasShown = false
    }

    private var counter = 0
    private fun publishResults() {
        for (i in votingList!!.indices) {
            val voting = votingList!![i]


            val answer = when (voting.answer) {
                Answers.REFRAIN -> "X"
                Answers.NOT_ANSWERED -> "X"
                Answers.NO -> "N"
                Answers.YES -> "Y"
            }

            val ballotVote = BallotVote(voting.id, answer)

            val api = RetrofitGenerator.create(ApiNikita::class.java)
            val call = api.ballotVote(ballotVote)
            counter++
            call.enqueue(object : Callback<JsonObject> {
                override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                    counter--
                    Log.d("ed__", "onFailure")
                    t.printStackTrace()

                    if (counter == 0) {
                        endUploading()
                    }
                }

                override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                    counter--
                    Log.d("ed__", "onResponse: Status:" + response.code().toString())
                    if (counter == 0) {
                        endUploading()
                    }
                }
            })
        }
    }

    private fun endUploading() {
        progressBar.visibility = View.GONE
        Toast.makeText(context!!, "Результаты отправлены", Toast.LENGTH_LONG).show()
        fragmentManager!!.popBackStackImmediate()
    }

    private fun getVotingList(): List<VotingModel> {
        return Votings.list
    }

    private fun pinCodeEntered() {
        view_.pinField.visibility = View.GONE
        view_.tvInfo.text = "Теперь вы можете принять участие в голосовании"
        for (v in this.votingList!!) {
            v.isLocked = false
        }
        updateVotingsUI()
    }

    fun updateVotingsUI() {
        view_.llVotings.removeAllViews()

        val list = this.votingList!!

        for (pos in list.indices) {

            if (list[pos].answer != Answers.NOT_ANSWERED) {
                showBtnSend()
            }

            val view =
                LayoutInflater.from(context).inflate(R.layout.rvitem_voting, view_.llVotings, false)
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
            view.setOnClickListener {
                showVotingDetails(pos)
            }
            view_.llVotings.addView(view)
        }
    }

    private fun showBtnSend() {
        btnSendResults.visibility = View.VISIBLE
    }

    private fun showVotingDetails(pos: Int) {
        val voting = votingList?.get(pos)
        val dialog = VotingDetailsDialog.newInstance(voting!!, pos + 1)
        dialog.show(childFragmentManager, null)
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