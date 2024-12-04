package com.example.yup

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.yup.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val apiKey = "7ae960dfb79d4fb98d611848761f6657" // Replace with your Spoonacular API Key

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.searchButton.setOnClickListener {
            val ingredients = binding.ingredientsInput.text.toString()
            if (ingredients.isNotBlank()) {
                fetchRecipes(ingredients)
            } else {
                Toast.makeText(this, "Please enter ingredients", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun fetchRecipes(ingredients: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val recipes = RetrofitInstance.api.getRecipes(ingredients, 10, apiKey)
                runOnUiThread {
                    val intent = Intent(this@MainActivity, ResultsActivity::class.java)
                    intent.putParcelableArrayListExtra("recipes", ArrayList(recipes))
                    startActivity(intent)
                }
            } catch (e: Exception) {
                // Log the exception for debugging
                runOnUiThread {
                    Toast.makeText(this@MainActivity, "Error Fetching Recipes", Toast.LENGTH_SHORT).show()
                }
                e.printStackTrace()
            }
        }
    }

}