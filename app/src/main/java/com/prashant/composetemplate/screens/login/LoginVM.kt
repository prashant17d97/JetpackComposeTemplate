package com.prashant.composetemplate.screens.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.google.gson.JsonElement
import com.prashant.composetemplate.R
import com.prashant.composetemplate.commonfunctions.CommonFunctions.getStringResource
import com.prashant.composetemplate.commonfunctions.CommonFunctions.showToast
import com.prashant.composetemplate.datastore.DataStoreUtil
import com.prashant.composetemplate.navigation.Screens
import com.prashant.composetemplate.network.ApiProcessor
import com.prashant.composetemplate.network.Repository
import com.prashant.composetemplate.network.RetrofitApi
import com.prashant.composetemplate.preferencefile.PreferenceFile
import com.prashant.composetemplate.screens.login.data.LoginData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class LoginVM @Inject constructor(
    private val preferenceFile: PreferenceFile,
    private val dataStore: DataStoreUtil,
    private val repository: Repository,
) : ViewModel() {

    fun login(navHostController: NavHostController) = viewModelScope.launch {
        repository.apiCall<LoginData>(
            retrofitCall = object : ApiProcessor {
                override suspend fun sendRequest(retrofitApi: RetrofitApi): Response<JsonElement> {
                    return retrofitApi.login("userId", "password")
                }
            },
            result = { result ->
                Log.e("TAG", "login: $result")
                navHostController.navigate(Screens.Home.route) {
                    popUpTo(Screens.Login.route) {
                        inclusive = true
                    }
                    launchSingleTop = true
                }
            },
            responseMessage = { message, code ->
                Log.e("TAG", "login: $message $code")
                message.takeIf { it.isNotEmpty() }
                    ?: "$code ${getStringResource(R.string.someError)}".showToast()
            })
    }
}