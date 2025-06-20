package com.example.myjogadores.network

import com.example.myjogadores.data.Jogador
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

// URL base para acesso ao servidor local (API Node/JSON)
const val BASE_URL = "http://10.0.2.2:3000/"

// Instância do Moshi com suporte para Kotlin
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

// Instância do Retrofit com Moshi como conversor
private val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .build()

// Interface com o endpoint da API
interface OpenJogadorApiService {
    @GET("jogadores")
    suspend fun getJogadores(): List<Jogador>
}

// Objeto singleton para acesso à API
object OpenJogadorApi {
    val retrofitService: OpenJogadorApiService by lazy {
        retrofit.create(OpenJogadorApiService::class.java)
    }
}
