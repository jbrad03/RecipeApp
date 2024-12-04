package com.example.yup

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class RecipeAdapter(
    private val recipes: List<Recipe>,
    private val onRecipeClick: (Recipe) -> Unit
) : RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {

    class RecipeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.recipe_title)
        val image: ImageView = itemView.findViewById(R.id.recipe_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recipe_item, parent, false)
        return RecipeViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val recipe = recipes[position]
        holder.title.text = recipe.title
        Glide.with(holder.itemView.context).load(recipe.image).into(holder.image)

        // Handle click
        holder.itemView.setOnClickListener {
            onRecipeClick(recipe)
        }
    }

    override fun getItemCount() = recipes.size
}