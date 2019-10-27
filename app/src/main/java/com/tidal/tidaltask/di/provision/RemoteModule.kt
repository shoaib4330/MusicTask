package com.tidal.tidaltask.di.provision

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.tidal.tidaltask.util.NetworkConstants
import com.tidal.tidaltask.backend.DeezerApiGateway
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
class RemoteModule{

    @Provides
    @Named("Deezer_Base_Url")
    fun getBaseUrl() : String = NetworkConstants.BASE_URL

    @Provides
    @Singleton
    fun getGson() : Gson = GsonBuilder().setLenient().setPrettyPrinting().create()

    @Provides
    @Singleton
    fun getLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }

    @Provides
    @Singleton
    fun getOkhttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        val client = OkHttpClient.Builder()
        client.addInterceptor(loggingInterceptor)
        client.connectTimeout(NetworkConstants.CONNECTION_TIMEOUT, TimeUnit.SECONDS)
        return client.build()
    }

    @Provides
    @Singleton
    fun getRetrofit(gson: Gson, okHttpClient: OkHttpClient, @Named("Deezer_Base_Url") baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun getGateway(retrofit: Retrofit): DeezerApiGateway {
        return retrofit.create(DeezerApiGateway::class.java)
    }
}