package com.example.yup

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.yup.databinding.ActivityRecipeDetailsBinding

class RecipeDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRecipeDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecipeDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Retrieve recipe data from the intent
        val recipe = intent.getParcelableExtra<Recipe>("recipe")

        recipe?.let {
            binding.recipeTitle.text = it.title
            Glide.with(this).load(it.image).into(binding.recipeImage)

            val ingredients = buildString {
                append("Ingredients:\n")
                it.usedIngredients.forEach { ingredient ->
                    append("- ${ingredient.original}\n")
                }
                it.missedIngredients.forEach { ingredient ->
                    append("- ${ingredient.original}\n")
                }
            }

            binding.recipeIngredients.text = ingredients
        }
    }
}