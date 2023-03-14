package com.prashant.composetemplate.screens.home

import androidx.lifecycle.ViewModel
import com.prashant.composetemplate.datastore.DataStoreUtil
import com.prashant.composetemplate.network.RetrofitApi
import com.prashant.composetemplate.preferencefile.PreferenceFile
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeVM @Inject constructor(
    private val preferenceFile: PreferenceFile,
    private val dataStore: DataStoreUtil,
    private val retrofitApi: RetrofitApi
) : ViewModel() {
}