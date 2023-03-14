package com.prashant.composetemplate.app

import android.content.Context
import android.content.SharedPreferences
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.prashant.composetemplate.datastore.DataStoreUtil
import com.prashant.composetemplate.datastore.dataStore
import com.prashant.composetemplate.network.RetrofitApi
import com.prashant.composetemplate.preferencefile.PreferenceFile
import com.prashant.composetemplate.preferencefile.preferenceName
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
@InstallIn(SingletonComponent::class)
@Module
class Module {


    /**
     * Providing Data Store
     * */
    @Provides
    @Singleton
    fun getDataStore(@ApplicationContext context: Context): DataStore<Preferences> =
        context.dataStore

    @Provides
    @Singleton
    fun exceptionHandler() = CoroutineExceptionHandler { _, t ->
        t.printStackTrace()

        CoroutineScope(Dispatchers.Main).launch {
//            hideProgress()
            t.printStackTrace()
        }
    }

    @Provides
    @Singleton
    fun dataStoreUtil(
        dataStore: DataStore<Preferences>,
        coRoutineExceptionHandler: CoroutineExceptionHandler
    ) = DataStoreUtil(dataStore, coRoutineExceptionHandler)


    /**
     * Providing preference store to store keys
     * */

    @Provides
    @Singleton
    fun getPref(@ApplicationContext context: Context): SharedPreferences =
        context.getSharedPreferences(preferenceName, Context.MODE_PRIVATE)

    @Provides
    @Singleton
    fun getEditor(sharedPreferences: SharedPreferences): SharedPreferences.Editor =
        sharedPreferences.edit()

    @Provides
    @Singleton
    fun preferenceFile(getPref: SharedPreferences, getEditor: SharedPreferences.Editor) =
        PreferenceFile(getEditor, getPref)


    @Provides
    fun provideBaseUrl() = "https://www.example.com/"


    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder()
            .readTimeout(1, TimeUnit.MINUTES)
            .connectTimeout(1, TimeUnit.MINUTES)
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Singleton
    @Provides
    fun gsonConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()

    @Singleton
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory,
        BASE_URL: String
    ): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(gsonConverterFactory)
        .addCallAdapterFactory(CoroutineCallAdapterFactory()).build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): RetrofitApi = retrofit.create(RetrofitApi::class.java)



}