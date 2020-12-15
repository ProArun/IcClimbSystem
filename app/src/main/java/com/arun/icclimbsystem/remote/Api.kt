package com.arun.icclimbsystem.remote

import com.arun.icclimbsystem.model.Users
import com.arun.icclimbsystem.model.UsersItem
import retrofit2.Response

import retrofit2.http.GET

interface Api {
    @GET("/users/")
    suspend fun searchForFaculty(): Response<Users>
}