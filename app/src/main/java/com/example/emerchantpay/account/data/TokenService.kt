package com.example.emerchantpay.account.data

import com.example.emerchantpay.account.domain.model.AccessTokenModel
import com.example.emerchantpay.account.domain.model.User
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

const val CLIENT_ID_KEY = "client_id"
const val CLIENT_SECRET_KEY = "client_secret"
const val REDIRECT_URI_KEY = "redirect_uri"
const val CODE = "code"
const val ACCESS_TOKEN = "access_token"

interface TokenService {
    @FormUrlEncoded
    @Headers("Accept: application/json")
    @POST("login/oauth/access_token")
    suspend fun getToken(
        @Field(CLIENT_ID_KEY) clientId: String,
        @Field(CLIENT_SECRET_KEY) clientSecret: String,
        @Field(CODE) code: String,
        @Field(REDIRECT_URI_KEY) redirectUri: String
    ): Response<AccessTokenModel>
}