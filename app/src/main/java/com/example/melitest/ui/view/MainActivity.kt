package com.example.melitest.ui.view

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.SearchView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.melitest.R
import com.example.melitest.core.utils.Utils.hideKeyboard
import com.example.melitest.core.utils.Utils.msgPlaceholder
import com.example.melitest.data.enums.Site
import com.example.melitest.data.model.ResultsModel
import com.example.melitest.databinding.ActivityMainBinding
import com.example.melitest.databinding.DialogCountryBinding
import com.example.melitest.ui.adapter.ProductsAdapter
import com.example.melitest.ui.viewmodel.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener,
    androidx.appcompat.widget.SearchView.OnQueryTextListener {

    private lateinit var binding: ActivityMainBinding

    private var dialogMainBinding: DialogCountryBinding? = null
    private var dialog: Dialog? = null
    private var siteSelected: String? = ""

    private val searchViewModel: SearchViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        val screenSplash = installSplashScreen()
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        screenSplash.setKeepOnScreenCondition { false }

        searchViewModel.onCreate()
        searchViewModel.searchModel.observe(this, Observer {
            val results = it?.results
            if (results != null) {
                loadResults(results)
            }
        })

        searchViewModel.isLoading.observe(this, Observer {
            binding.pbloading.isVisible = it
        })

        loadDialogCountry()
        binding.svProducts.setOnQueryTextListener(this)

        binding.fbModal.setOnClickListener {
            loadDialogCountry()
        }
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if (query != null) {
            searchProducts(query)
            val view: View? = this.currentFocus
            if (view != null) {
                hideKeyboard(view, binding.svProducts.context)
            }
        };
        return true
    }

    override fun onQueryTextChange(query: String?): Boolean {
        return true
    }

    private fun searchProducts(search: String) {
        searchViewModel.searchProducts(siteSelected, search)
    }


    private fun loadResults(results: MutableList<ResultsModel>) {

        binding.rvProducts.setHasFixedSize(true)

        val divider = DividerItemDecoration(
            binding.rvProducts.context, DividerItemDecoration.VERTICAL
        )
        divider.setDrawable(ContextCompat.getDrawable(baseContext, R.drawable.divider)!!)
        binding.rvProducts.addItemDecoration(divider)

        binding.rvProducts.layoutManager = LinearLayoutManager(this)
        binding.rvProducts.adapter = ProductsAdapter(results)
    }

    private fun loadDialogCountry() {
        dialogMainBinding = DialogCountryBinding.inflate(layoutInflater)
        dialog = Dialog(this)
        dialog!!.window!!.setBackgroundDrawable(
            ColorDrawable(
                Color.TRANSPARENT
            )
        )
        dialog!!.setContentView(dialogMainBinding!!.root)

        var options = mutableListOf("Seleccione")
        options.addAll(Site.values().map { it.name })

        val adapter = ArrayAdapter(
            this, android.R.layout.simple_spinner_item, options
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        dialogMainBinding!!.spSite.adapter = adapter
        dialogMainBinding!!.titleDialog.isVisible = siteSelected.isNullOrEmpty()

        dialogMainBinding!!.spSite.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(parent: AdapterView<*>?) {

                }

                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    var optionSelected = parent!!.getItemAtPosition(position).toString()

                    if (optionSelected != "Seleccione") {
                        siteSelected = optionSelected
                        binding.svProducts.queryHint = msgPlaceholder(siteSelected, parent.context)

                        binding.svProducts.requestFocus()
                        dialog!!.dismiss()
                    }
                }

            }


        dialog!!.setCancelable(false)
        dialog!!.show()
    }
}