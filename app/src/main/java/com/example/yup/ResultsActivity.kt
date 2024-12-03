package com.example.yup

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.yup.databinding.ActivityResultsBinding
import com.example.yup.Recipe


class ResultsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResultsBinding
    private var currentIndex = 0
    private lateinit var recipes: List<Recipe>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val gson = Gson()
        val recipesJson = intent.getStringExtra("recipes_json") // Retrieve JSON string
        val type = object : TypeToken<List<Recipe>>() {}.type
        recipes = gson.fromJson(recipesJson, type) ?: emptyList() // Convert JSON to List<Recipe>

        if (recipes.isEmpty()) {
            finish()
            return
        }

        displayRecipe()

        binding.detailsButton.setOnClickListener {
            currentIndex = (currentIndex + 1) % recipes.size
            displayRecipe()
        }
    }

    private fun displayRecipe() {
        val recipe = recipes[currentIndex]
        binding.mealDescription.text = recipe.title
        Glide.with(this).load(recipe.image).into(binding.mealImage)
    }
}