package com.example.json

import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("wp-json/wp/v2/posts")
    fun getArticles(): Call<List<Article>>
}