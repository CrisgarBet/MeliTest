package com.example.melitest.ui.view

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.melitest.R
import com.example.melitest.core.utils.Utils
import com.example.melitest.core.utils.Utils.getSerializable
import com.example.melitest.core.utils.Utils.getSpecificString
import com.example.melitest.data.model.ResultsModel
import com.example.melitest.databinding.ActivityDetailProductBinding
import com.squareup.picasso.Picasso
import kotlin.math.roundToInt

class DetailProduct : AppCompatActivity() {

    private lateinit var binding: ActivityDetailProductBinding
    private var resultsModel: ResultsModel = ResultsModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

        resultsModel =
            getSerializable(intent, "resultsModel", ResultsModel::class.java)

        loadDataDetail()

    }

    @SuppressLint("SetTextI18n")
    private fun loadDataDetail() {

        binding.titleSite.text = resultsModel.siteId
        binding.tvPrice.text = Utils.convertMoney(
            resultsModel.siteId, resultsModel.sellerAddress?.country?.id, resultsModel.price
        )
        binding.tvCondition.text = getSpecificString(resultsModel.siteId, this, "condition")
        binding.tvTitle.text = resultsModel.title
        Picasso.get().load(resultsModel.thumbnail)
            .placeholder(R.drawable.meli_welcome).fit()
            .centerInside()
            .error(R.drawable.meli_welcome).into(binding.ivProduct)
        binding.tvCuotes.text = Utils.formatMessagePayments(
            resultsModel.installments?.quantity, binding.tvCuotes.context
        ) + Utils.convertMoney(
            resultsModel.siteId,
            resultsModel.sellerAddress?.country?.id,
            resultsModel.installments?.amount
        )
        if (resultsModel.shipping?.freeShipping == true) {
            binding.tvSend.text =
                getSpecificString(resultsModel.siteId, binding.tvSend.context, "msg_shipping")
        }

        if (resultsModel.orderBackend!! > 1) {
            binding.tvAvailable.text = Utils.msgAvailable(
                resultsModel.siteId, binding.tvSend.context, resultsModel.orderBackend
            )
        }

        binding.rbSeller.rating =
            Utils.formatRating(resultsModel.seller?.sellerReputation?.transactions?.ratings)


        binding.titleStock.text =
            getSpecificString(resultsModel.siteId, binding.titleStock.context, "title_stock")

        if (resultsModel.soldQuantity!! > 1) {

            binding.tvcantidaddisponibles.text = getSpecificString(
                resultsModel.siteId,
                binding.tvcantidaddisponibles.context,
                "stock"
            ).replace(
                "%d",
                resultsModel.soldQuantity!!.roundToInt().toString()
            )
        }

        binding.tvcantidad.text = getSpecificString(resultsModel.siteId, binding.tvcantidad.context, "unit_stock")
        binding.btncomprar.text =
            getSpecificString(resultsModel.siteId, binding.tvcantidad.context, "buy")
    }
}