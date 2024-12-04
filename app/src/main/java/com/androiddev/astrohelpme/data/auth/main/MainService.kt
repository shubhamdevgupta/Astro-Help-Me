package com.androiddev.astrohelpme.data.auth.main

import com.androiddev.astrohelpme.data.auth.AstroDetailsRequest
import com.androiddev.astrohelpme.data.auth.MatchMakingRequest
import com.androiddev.astrohelpme.data.response.KundliResponse
import com.androiddev.astrohelpme.data.response.matchmaking.MatchMakingResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface MainService {
    @POST("astro_details")
    suspend fun getAstroDetails(@Body request: AstroDetailsRequest): Response<KundliResponse>

   @POST("match_manglik_report")
    suspend fun getMatchMaking(@Body request: MatchMakingRequest): Response<MatchMakingResponse>




}