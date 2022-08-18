package com.example.myapplication


import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitService {

    @GET("{CEP}/json/")
    fun getCEP(@Path("CEP") CEP: String): Call<CEP>

    @GET("{estado}/{cidade}/{endereco}/json/")
    fun getRCE(
        @Path("estado") estado: String,
        @Path("cidade") cidade: String,
        @Path("endereco") endereco: String
    ): Call<List<CEP>>
}