package com.androiddev.astrohelpme.data.response.matchmaking

import java.io.Serializable

data class MatchMakingResponse(
    val conclusion: Conclusion,
    val female: Female,
    val male: Male
):Serializable