package io.github.dvegasa.cp2020rnd.features.voting_screen

/**
 * Created by Ed Khalturin @DVegasa
 */

enum class Answers {
    NOT_ANSWERED, YES, NO, REFRAIN
}

data class VotingModel(
    var id: Long,
    var title: String,
    var message: String,
    var photos: List<Int>,
    var answer: Answers = Answers.NOT_ANSWERED,
    var isLocked: Boolean = true
)