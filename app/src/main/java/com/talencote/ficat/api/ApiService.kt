package com.talencote.ficat.api

import com.talencote.ficat.data.dto.*
import com.talencote.ficat.utilities.Constants
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

    @POST(Constants.LOGIN_URL)
    fun login(@Body request: LoginRequest): Call<LoginResponse>

    @POST(Constants.REG_URL)
    fun registration(@Body request: RegistrationRequest): Call<MessageResponse>

    @GET(Constants.GET_POPULAR_FANFICS)
    suspend fun popular(@Path("page") page : Int): List<FanficDto>

    @POST(Constants.TEST)
    fun testToken(): Call<Boolean>

    @GET(Constants.GET_FANFIC)
    suspend fun getFanfic(@Path("id") id : Int): FanficDto

    @GET(Constants.GET_FANDOMS_STRING)
    suspend fun getFandomsString(@Path("id") id: Long): MessageResponse

    @POST(Constants.POST_FANDOMS_STRING)
    fun setFandomsString(@Path("id") id: Long, @Body fandoms: FandomsDto): Call<MessageResponse>
}