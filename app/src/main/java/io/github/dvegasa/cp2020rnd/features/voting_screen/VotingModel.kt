package io.github.dvegasa.cp2020rnd.features.voting_screen

/**
 * Created by Ed Khalturin @DVegasa
 */

enum class Answers {
    NOT_ANSWERED, YES, NO, REFRAIN
}

data class VotingModel(
    val id: Long,
    val title: String,
    val message: String,
    val photos: List<Int>,
    val answer: Answers = Answers.NOT_ANSWERED,
    val isLocked: Boolean = true
)