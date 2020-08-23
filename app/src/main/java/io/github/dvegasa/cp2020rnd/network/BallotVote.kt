package io.github.dvegasa.cp2020rnd.network


import com.google.gson.annotations.SerializedName


data class BallotVote(
    @SerializedName("pollId")
    val pollId: String,
    @SerializedName("vote")
    val vote: String,
    @SerializedName("userId")
    val userId: String = "5f41d5fdabf767002bf8abe7"
)