package com.androiddev.astrohelpme.data.auth.main

import com.androiddev.astrohelpme.data.auth.AstroDetailsRequest
import com.androiddev.astrohelpme.data.auth.MatchMakingRequest
import com.androiddev.astrohelpme.data.response.KundliResponse
import com.androiddev.astrohelpme.data.response.matchmaking.MatchMakingResponse
import com.androiddev.astrohelpme.data.response.numerology.NumerologyResponse
import com.androiddev.astrohelpme.data.response.panchang.PanchangResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface MainService {
    @POST("astro_details")
    suspend fun getAstroDetails(@Body request: AstroDetailsRequest): Response<KundliResponse>

    @POST("match_manglik_report")
    suspend fun getMatchMaking(@Body request: MatchMakingRequest): Response<MatchMakingResponse>

    @POST("numero_table")
    suspend fun getNumerologyData(@Body request: NumerologyRequest): Response<NumerologyResponse>

    @POST("basic_panchang")
    suspend fun getPanchangData(@Body request: PanchangRequest): Response<PanchangResponse>



}