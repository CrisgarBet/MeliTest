package com.example.melitest.ui.adapter

import android.annotation.SuppressLint
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.melitest.R
import com.example.melitest.core.utils.Utils.convertMoney
import com.example.melitest.core.utils.Utils.formatMessagePayments
import com.example.melitest.core.utils.Utils.formatRating
import com.example.melitest.core.utils.Utils.msgAvailable
import com.example.melitest.core.utils.Utils.msgShipping
import com.example.melitest.data.model.ResultsModel
import com.example.melitest.databinding.ItemProductBinding
import com.squareup.picasso.Picasso

class ProductsViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = ItemProductBinding.bind((view))

    @SuppressLint("SetTextI18n")
    fun render(resultsModel: ResultsModel) {
        binding.tvTitle.text = resultsModel.title
        binding.tvPrice.text = convertMoney(
            resultsModel.siteId, resultsModel.sellerAddress?.country?.id, resultsModel.price
        )
        Picasso.get().load(resultsModel.thumbnail)
            .placeholder(R.drawable.meli).resize(300, 300)
            .centerInside()
            .error(R.drawable.meli).into(binding.ivProduct)
        binding.tvCuotes.text = formatMessagePayments(
            resultsModel.installments?.quantity, binding.tvCuotes.context
        ) + convertMoney(
            resultsModel.siteId,
            resultsModel.sellerAddress?.country?.id,
            resultsModel.installments?.amount
        )
        if (resultsModel.shipping?.freeShipping == true) {
            binding.tvSend.text = msgShipping(resultsModel.siteId, binding.tvSend.context)
        }

        if (resultsModel.orderBackend!! > 1) {
            binding.tvAvailable.text = msgAvailable(
                resultsModel.siteId, binding.tvSend.context, resultsModel.orderBackend
            )
        }
        binding.rbSeller.rating =
            formatRating(resultsModel.seller?.sellerReputation?.transactions?.ratings)
    }

}