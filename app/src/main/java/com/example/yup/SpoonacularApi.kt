package com.example.yup

import retrofit2.http.GET
import retrofit2.http.Query

interface SpoonacularApi {
    @GET("recipes/findByIngredients")
    suspend fun getRecipes(
        @Query("ingredients") ingredients: String,  // Comma-separated list of ingredients
        @Query("number") number: Int = 10,          // Maximum number of recipes (default 10)
        @Query("ranking") ranking: Int = 1,         // Maximize used ingredients (1) or minimize missing (2)
        @Query("apiKey") apiKey: String             // Your API key
    ): List<Recipe>
}