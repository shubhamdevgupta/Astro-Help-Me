package com.androiddev.astrohelpme.di

import com.airbnb.lottie.BuildConfig
import com.androiddev.astrohelpme.data.auth.AuthService
import com.androiddev.astrohelpme.data.auth.main.MainService
import com.androiddev.astrohelpme.utils.api.ApiInterceptor
import com.androiddev.astrohelpme.utils.helper.AppConstants
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton
@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Provides
    @Named("BaseUrl1")
    fun provideBaseUrl1() = AppConstants.BASE_URL

    @Provides
    @Named("BaseUrl2")
    fun provideBaseUrl2() = "https://json.astrologyapi.com/v1/"

    @Provides
    @Singleton
    fun provideOkHttpClient(apiInterceptor: ApiInterceptor) = if (BuildConfig.DEBUG) {
        OkHttpClient
            .Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .build()
    } else {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(apiInterceptor)
            .connectTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .build()
    }

    @Singleton
    @Provides
    @Named("Retrofit1")
    fun provideRetrofit1(
        okHttpClient: OkHttpClient,
        @Named("BaseUrl1") baseUrl: String
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder()
                        .serializeNulls()
                        .setLenient()
                        .create()
                )
            )
            .client(okHttpClient)
            .build()
    }

    @Singleton
    @Provides
    @Named("Retrofit2")
    fun provideRetrofit2(
        okHttpClient: OkHttpClient,
        @Named("BaseUrl2") baseUrl: String
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder()
                        .serializeNulls()
                        .setLenient()
                        .create()
                )
            )
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideAuthService(@Named("Retrofit1") retrofit: Retrofit): AuthService =
        retrofit.create(AuthService::class.java)

    @Provides
    @Singleton
    fun provideMainService(@Named("Retrofit2") retrofit: Retrofit): MainService =
        retrofit.create(MainService::class.java)
}
