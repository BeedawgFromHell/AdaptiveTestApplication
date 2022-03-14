package com.example.adaptivetestapplication.data

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface ImagesService {

    @GET("task-m-001/list.php")
    suspend fun getImages(): Response<List<String>?>
}