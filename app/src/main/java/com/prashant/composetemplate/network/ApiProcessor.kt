package com.prashant.composetemplate.network

import com.google.gson.JsonElement
import retrofit2.Response

interface ApiProcessor {
    suspend fun sendRequest(retrofitApi: RetrofitApi):Response<JsonElement>
}