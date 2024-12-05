package com.androiddev.astrohelpme.data.response.numerology

import java.io.Serializable

data class NumerologyResponse(
    val date: String,
    val destiny_number: Int,
    val evil_num: String,
    val fav_color: String,
    val fav_day: String,
    val fav_god: String,
    val fav_mantra: String,
    val fav_metal: String,
    val fav_stone: String,
    val fav_substone: String,
    val friendly_num: String,
    val name: String,
    val name_number: Int,
    val neutral_num: String,
    val radical_num: String,
    val radical_number: Int,
    val radical_ruler: String
):Serializable