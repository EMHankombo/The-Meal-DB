package com.example.themealdb.ui.recipes.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.themealdb.R
import com.example.themealdb.model.recipes.Meal
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.meal_item.view.*

class RecipesAdapter(private var meals: List<Meal>) :
    RecyclerView.Adapter<RecipesAdapter.MealViewHolder>() {

    fun setMealsData(meals: List<Meal>) {
        this.meals = meals
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.meal_item, parent, false)

        return MealViewHolder(view)
    }

    override fun getItemCount(): Int {
        return meals.size
    }

    override fun onBindViewHolder(holder: MealViewHolder, position: Int) {
        val meal = meals[position]

        holder.apply {
            mealName.text = meal.strMeal
            Picasso.get().load(meal.strMealThumb).into(mealImg)
        }
    }

    class MealViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val mealName: TextView = itemView.tv_meal_name
        val mealImg: ImageView = itemView.img_meal

    }
}