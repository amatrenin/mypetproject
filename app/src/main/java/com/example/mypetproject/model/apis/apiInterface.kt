package com.example.mypetproject

import com.example.mypetproject.data.Movies
import com.example.mypetproject.data.MoviesActors
import com.example.mypetproject.data.MoviesDetails
import com.example.mypetproject.data.MoviesVideos
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {

    @GET("3/movie/popular")
    fun getMovies(@Query("api_key") sort: String,
                  @Query("language") language: String,
                  @Query("page") page: Int): Call<Movies>

    @GET("3/movie/{movie_id}")
    fun getMoviesDetails(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String,
    ): Call<MoviesDetails>

    @GET("3/person/{person_id}")
    fun getActors(
        @Path("person_id") id: Int,
        @Query("api_key") apiKey: String,
    ): Call<MoviesActors>

    @GET("3/movie/{movie_id}/videos")
    fun getVideos(@Path("movie_id") movieId: Int,
                  @Query("api_key") sort: String
    ): Call<MoviesVideos>

    companion object {

        var BASE_URL = "https://api.themoviedb.org/"

        fun create(): ApiInterface {

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(ApiInterface::class.java)

        }
    }
}