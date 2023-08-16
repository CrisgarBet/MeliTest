package com.example.melitest.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.melitest.R
import com.example.melitest.data.model.ResultsModel

class ProductsAdapter(
    private var productsList: MutableList<ResultsModel> = ArrayList(),
    private val onClickListener: (ResultsModel) -> Unit
) :
    RecyclerView.Adapter<ProductsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ProductsViewHolder(layoutInflater.inflate(R.layout.item_product, parent, false))
    }

    override fun getItemCount(): Int = productsList.size


    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) {

        val item = productsList[position]
        holder.render(item, onClickListener)
    }


}