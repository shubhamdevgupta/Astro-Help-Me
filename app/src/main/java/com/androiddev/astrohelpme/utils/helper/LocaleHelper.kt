package com.androiddev.astrohelpme.utils.helper

import android.content.Context
import android.util.Log
import java.util.Locale

object LocaleHelper {

    fun setLocale(context: Context?, language: String): Context? {
        if (context==null){
            return null
        }
        val locale = Locale(language)
        Locale.setDefault(locale)
        val res = context.resources
        val config = res?.configuration
        config?.setLocale(locale)
        config?.setLayoutDirection(locale)
        return config?.let { context.createConfigurationContext(it) }
    }


    fun getLanguage(context: Context): String {
        val locale = context.resources.configuration.locales.get(0)
        return locale.language
    }
}
