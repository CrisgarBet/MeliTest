package com.example.melitest.ui.view

import android.annotation.SuppressLint
import android.graphics.Typeface
import android.os.Bundle
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.melitest.R
import com.example.melitest.core.utils.Utils
import com.example.melitest.core.utils.Utils.getSerializable
import com.example.melitest.core.utils.Utils.getSizeInDp
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

        binding.hearsite.setOnClickListener {
            setResult(RESULT_OK)
            finish()
        }

        resultsModel =
            getSerializable(intent, "resultsModel", ResultsModel::class.java)

        loadDataDetail()

    }

    @SuppressLint("SetTextI18n", "ResourceAsColor", "RtlHardcoded")
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

        binding.tvcantidad.text =
            getSpecificString(resultsModel.siteId, binding.tvcantidad.context, "unit_stock")
        binding.btncomprar.text =
            getSpecificString(resultsModel.siteId, binding.btncomprar.context, "buy")
        binding.btncarrito.text =
            getSpecificString(resultsModel.siteId, binding.btncarrito.context, "cart")

        binding.tvTitleSeller.text =
            getSpecificString(resultsModel.siteId, binding.tvTitleSeller.context, "seller_title")


        binding.tvsellerSales.text =
            "${resultsModel.sellerAddress?.state?.name} ${resultsModel.sellerAddress?.country?.name}"

        binding.tvsellernickname.text = resultsModel.seller?.nickname

        if (resultsModel.seller?.eshop != null) {
            Picasso.get().load(resultsModel.seller?.eshop?.eshopLogoUrl)
                .placeholder(R.drawable.meli).fit().centerInside()
                .error(R.drawable.meli).into(binding.ivSeller)
        }


        binding.tvTitleProductDetail.text =
            getSpecificString(
                resultsModel.siteId,
                binding.tvTitleProductDetail.context,
                "product_title"
            )


        binding.rbSellerrating.rating =
            Utils.formatRating(resultsModel.seller?.sellerReputation?.transactions?.ratings)

        val statusMercadolider = resultsModel.seller?.sellerReputation?.powerSellerStatus

        if (!statusMercadolider.isNullOrEmpty()) {
            var msgMercadolider = ""

            when (statusMercadolider) {
                "gold" -> msgMercadolider = getString(R.string.gold)
                "platinum" -> msgMercadolider = getString(R.string.platinum)
            }
            binding.tvsellerstatus.text = msgMercadolider
        }

        if (!resultsModel.attributes?.isEmpty()!!) {

            for (attribute in resultsModel.attributes!!) {

                val layout = LinearLayout(this)

                layout.orientation = LinearLayout.HORIZONTAL
                layout.gravity = Gravity.LEFT

                val tvName = TextView(this)
                val tvValue = TextView(this)

                tvName.text = attribute.name
                tvName.setTextColor(getColor(R.color.black))
                tvName.textSize =
                    getSizeInDp(tvName.context, R.dimen.payment_text).toFloat()
                tvName.setTypeface(null, Typeface.BOLD)

                tvValue.text = attribute.name
                tvValue.setTextColor(getColor(R.color.gray_meli))
                tvValue.textSize =
                    getSizeInDp(tvValue.context, R.dimen.payment_text).toFloat()
                tvValue.text = attribute.valueName

                val param: LinearLayout.LayoutParams =
                    LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.MATCH_PARENT
                    )
                param.setMargins(
                    0, 0, getSizeInDp(tvName.context, R.dimen.margin_item_seller), 0
                )
                tvName.layoutParams = param
                layout.addView(tvName)
                layout.addView(tvValue)
                binding.container.addView(layout)
            }

        }
    }
}