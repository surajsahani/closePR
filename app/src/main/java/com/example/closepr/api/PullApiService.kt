package com.example.closepr.api

import com.example.closepr.models.Pull
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url


interface PullApiService {

    @GET("repos/{user}/{repo}/pulls?state=closed")
    suspend fun getAllPulls(
        @Path(value = "user", encoded = true) user: String,
        @Path(value = "repo", encoded = true) repo: String
    ): Response<List<Pull>>

    @GET
    suspend fun getPullsFromLink(@Url link: String): Response<List<Pull>>

}