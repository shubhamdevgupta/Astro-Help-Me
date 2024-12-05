package com.androiddev.astrohelpme.data.repository

import com.androiddev.astrohelpme.data.auth.AstroDetailsRequest
import com.androiddev.astrohelpme.data.auth.MatchMakingRequest
import com.androiddev.astrohelpme.data.auth.main.MainService
import com.androiddev.astrohelpme.data.auth.main.NumerologyRequest
import com.androiddev.astrohelpme.data.auth.main.PanchangRequest
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainRepository @Inject constructor(private val mainService: MainService) {
    suspend fun getAstroDetails(astroDetailsRequest: AstroDetailsRequest) =
        mainService.getAstroDetails(astroDetailsRequest)

    suspend fun getMatchMaking(matchMakingRequest: MatchMakingRequest) = mainService.getMatchMaking(matchMakingRequest)

    suspend fun getNumerologyData(numerologyRequest: NumerologyRequest) = mainService.getNumerologyData(numerologyRequest)

    suspend fun getPanchangData(panchangRequest: PanchangRequest) = mainService.getPanchangData(panchangRequest)

}