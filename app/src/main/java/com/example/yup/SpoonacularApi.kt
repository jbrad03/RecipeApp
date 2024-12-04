package com.example.spoonacularapp.api

import com.example.yup.Recipe
import retrofit2.http.GET
import retrofit2.http.Query

interface SpoonacularApi {
    @GET("recipes/findByIngredients")
    suspend fun getRecipes(
        @Query("ingredients") ingredients: String,
        @Query("number") number: Int,
        @Query("apiKey") apiKey: String
    ): List<Recipe>
}