package com.example.yup

import com.google.gson.Gson
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.spoonacularapp.network.RetrofitInstance
import com.example.yup.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val apiKey = "YOUR_SPOONACULAR_API_KEY"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.searchButton.setOnClickListener {
            val ingredients = binding.ingredientsInput.text.toString()
            if (ingredients.isBlank()) {
                Toast.makeText(this, "Please enter ingredients", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            fetchRecipes(ingredients)
        }
    }

    private fun fetchRecipes(ingredients: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val recipes = RetrofitInstance.api.getRecipesByIngredients(ingredients, apiKey)
                withContext(Dispatchers.Main) {
                    val gson = Gson()
                    val recipesJson = gson.toJson(recipes) // Convert list to JSON string

                    val intent = Intent(this@MainActivity, ResultsActivity::class.java)
                    intent.putExtra("recipes_json", recipesJson) // Pass JSON string
                    startActivity(intent)
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@MainActivity, "Error fetching recipes", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }
}