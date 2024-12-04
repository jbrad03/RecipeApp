package com.example.yup
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Recipe(
    val id: Int,
    val title: String,
    val image: String,
    val usedIngredientCount: Int,
    val missedIngredientCount: Int,
    val missedIngredients: List<Ingredient>,
    val usedIngredients: List<Ingredient>,
    val likes: Int
) :Parcelable

@Parcelize
data class Ingredient(
    val id: Int,
    val amount: Double,
    val unit: String,
    val name: String,
    val original: String,
    val image: String
) : Parcelable