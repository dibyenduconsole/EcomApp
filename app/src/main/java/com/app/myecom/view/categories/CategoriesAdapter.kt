package com.app.myecom.view.categories

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.myecom.databinding.ItemRowCategoriesBinding
import java.util.ArrayList

class CategoriesAdapter (clickHandler: CategoriesClickHandler) : RecyclerView.Adapter<CategoriesAdapter.CategoriesViewHolder>(){

    var categoriesList: ArrayList<CategoriesData> = arrayListOf()
    var clickPresenter: CategoriesClickHandler = clickHandler

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemRowCategoriesBinding.inflate(inflater, parent, false)


        return CategoriesViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return if (categoriesList != null) {
            categoriesList.size
        } else {
            0
        }

    }

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {

        val product = categoriesList.get(position)
        holder.bind(product, clickPresenter)

    }


    class CategoriesViewHolder(private val itemRowCategoriesBinding: ItemRowCategoriesBinding) :
        RecyclerView.ViewHolder(itemRowCategoriesBinding.getRoot()){

        fun bind(categoriesData: CategoriesData, clickPresenter: CategoriesClickHandler) {
            itemRowCategoriesBinding.categories = categoriesData
            itemRowCategoriesBinding.handler = clickPresenter
            //itemMyBagListBinding.promotionItemSpecialLabel.visibility = if(item.isSpecial) View.VISIBLE else View.GONE
            itemRowCategoriesBinding.executePendingBindings()
        }

    }


    fun setCategoriesList(catsList: List<CategoriesData>) {
        categoriesList?.clear()
        categoriesList?.addAll(catsList)
        notifyDataSetChanged()
    }

}