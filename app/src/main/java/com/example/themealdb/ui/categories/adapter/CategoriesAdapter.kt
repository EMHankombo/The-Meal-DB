package com.example.themealdb.ui.categories.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.themealdb.R
import com.example.themealdb.model.categories.Category
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.category_item.view.*

class CategoriesAdapter(
    private var categories: MutableList<Category>, private val clickListener: (String) -> Unit
) : RecyclerView.Adapter<CategoriesAdapter.CategoryHolder>() {


    fun setCategoriesData(categories: MutableList<Category>) {
        this.categories = categories
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.category_item, parent, false)
        return CategoryHolder(view)
    }

    override fun getItemCount(): Int {
        return categories.size
    }

    override fun onBindViewHolder(holder: CategoryHolder, position: Int) {
        val category = categories[position]

        holder.bind(category,clickListener)
    }

    class CategoryHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val categoryName = itemView.tv_category_name
        private val categoryImage = itemView.img_category

        fun bind(
            category: Category,
            clickListener: (String) -> Unit
        ) {

            categoryName.text = category.strCategory
            Picasso.get().load(category.strCategoryThumb).into(categoryImage)

            itemView.setOnClickListener {
                clickListener.invoke(category.strCategory)
            }
        }


    }
}