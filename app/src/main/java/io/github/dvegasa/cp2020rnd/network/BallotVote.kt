package io.github.dvegasa.cp2020rnd.network


import com.google.gson.annotations.SerializedName

/*
vote: 
5f4164dfe8d1dc02e57f168a
5f4164e4e8d1dc02e57f168b
5f4164eae8d1dc02e57f168c
5f416254ea0e89005887a838
 */

data class BallotVote(
    @SerializedName("id")
    val id: String,
    @SerializedName("vote")
    val vote: String,
    @SerializedName("user")
    val user: String = "5f415965cb91bf003054217c",
    @SerializedName("voteValue")
    val voteValue: Int = 100
)