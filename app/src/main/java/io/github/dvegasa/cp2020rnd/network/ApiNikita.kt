package io.github.dvegasa.cp2020rnd.network

import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * Created by Ed Khalturin @DVegasa
 */
interface ApiNikita {
    @POST("ballot/vote")
    fun ballotVote(@Body ballotVote: BallotVote): Call<JsonObject>
}