package com.example.spoonacularapp.api

import com.example.spoonacularapp.models.Recipe
import retrofit2.http.GET
import retrofit2.http.Query

interface SpoonacularApi {
    @GET("recipes/findByIngredients")
    suspend fun getRecipesByIngredients(
        @Query("ingredients") ingredients: String,
        @Query("apiKey") apiKey: String
    ): List<Recipe>
}