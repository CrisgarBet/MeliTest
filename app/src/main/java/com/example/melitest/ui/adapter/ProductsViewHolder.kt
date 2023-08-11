package com.example.melitest.ui.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.melitest.R
import com.example.melitest.data.model.ResultsModel

class ProductsViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val product = view.findViewById<TextView>(R.id.txtname)
    fun render(resultsModel: ResultsModel){
        product.setText(resultsModel.title)
    }

}