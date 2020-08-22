package io.github.dvegasa.cp2020rnd.features.voting_screen

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by Ed Khalturin @DVegasa
 */

enum class Answers {
    NOT_ANSWERED, YES, NO, REFRAIN
}

@Parcelize
data class VotingModel(
    var id: String,
    var title: String,
    var message: String,
    var answer: Answers = Answers.NOT_ANSWERED,
    var isLocked: Boolean = true
) : Parcelable