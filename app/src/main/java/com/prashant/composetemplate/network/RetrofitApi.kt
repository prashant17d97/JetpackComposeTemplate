package com.prashant.composetemplate.network

import com.google.gson.JsonElement
import com.google.gson.JsonObject
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*
interface RetrofitApi {

    @GET(LOGIN_API)
    suspend fun login(
        @Query("EmailID") userId: String,
        @Query("Password") password: String
    ): Response<JsonElement>

}