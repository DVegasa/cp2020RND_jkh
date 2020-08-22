package io.github.dvegasa.cp2020rnd.features.voting_screen

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import com.poovam.pinedittextfield.PinField
import io.github.dvegasa.cp2020rnd.R
import kotlinx.android.synthetic.main.fragment_voting.*
import kotlinx.android.synthetic.main.fragment_voting.view.*


class VotingFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_voting, container, false)

        view.apply {
            btnSmsRequest.setOnClickListener {
                btnSmsRequest.visibility = View.GONE
                pinField.visibility = View.VISIBLE
                tvInfo.text = "Введите код из СМС-сообщения\nВ демо-версии можно ввести любые цифры"

                pinField.requestFocus()
                val imm: InputMethodManager? =
                    context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm!!.showSoftInput(pinField, InputMethodManager.SHOW_IMPLICIT)
            }

            pinField.onTextCompleteListener = object : PinField.OnTextCompleteListener {
                override fun onTextComplete(enteredText: String): Boolean {
                    pinCodeEntered()
                    return true
                }
            }
        }
        return view
    }

    private fun pinCodeEntered() {
        pinField.visibility = View.GONE
        tvInfo.text = "Теперь вы можете принять участие в голосовании"
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