package com.therealbluepandabear.restaurantsapp.restaurants.data.remote

import com.therealbluepandabear.restaurantsapp.restaurants.data.remote.RemoteRestaurant
import retrofit2.http.GET
import retrofit2.http.Query

interface RestaurantsApiService {
    @GET("restaurants.json")
    suspend fun getRestaurants(): List<RemoteRestaurant>

    @GET("restaurants.json?orderBy=\"r_id\"")
    suspend fun getRestaurant(@Query("equalTo") id: Int): Map<String, RemoteRestaurant>
}