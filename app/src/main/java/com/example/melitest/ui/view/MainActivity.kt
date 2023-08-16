package com.example.melitest.ui.view

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.SearchView
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.melitest.R
import com.example.melitest.core.utils.Utils.getSpecificString
import com.example.melitest.core.utils.Utils.hideKeyboard
import com.example.melitest.core.utils.Utils.isOnline
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
    private var options = mutableListOf("")

    private val searchViewModel: SearchViewModel by viewModels()

    private val startForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK) {
                loadDialogCountry()
            }
        }


    override fun onCreate(savedInstanceState: Bundle?) {
        val screenSplash = installSplashScreen()
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        screenSplash.setKeepOnScreenCondition { false }

        searchViewModel.onCreate()
        searchViewModel.searchModel.observe(this) {
            val results = it?.results
            if (!results.isNullOrEmpty()) {
                loadResults(results)
            } else{
                Toast.makeText(this,R.string.msg_error, Toast.LENGTH_LONG).show()
            }
        }

        searchViewModel.isLoading.observe(this) {
            binding.pbloading.isVisible = it
        }

        searchViewModel.loadOptions.observe(this) {
            options.addAll(it)
            loadDialogCountry()
        }
        binding.svProducts.setOnQueryTextListener(this)

        binding.hearsite.setOnClickListener {
            loadDialogCountry()
        }
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if (!query.isNullOrEmpty()) {
            if (isOnline(this)){
                searchProducts(query)
            }
        }
        return true
    }

    override fun onQueryTextChange(query: String?): Boolean {
        return true
    }

    private fun searchProducts(search: String?) {
        searchViewModel.searchProducts(siteSelected, search)
        val view: View? = this.currentFocus
        if (view != null) {
            hideKeyboard(view, binding.svProducts.context)
        }
    }


    private fun loadResults(results: MutableList<ResultsModel>) {

        binding.rvProducts.setHasFixedSize(true)

        val divider = DividerItemDecoration(
            binding.rvProducts.context, DividerItemDecoration.VERTICAL
        )
        divider.setDrawable(ContextCompat.getDrawable(baseContext, R.drawable.divider)!!)
        binding.rvProducts.addItemDecoration(divider)

        binding.rvProducts.layoutManager = LinearLayoutManager(this)
        binding.rvProducts.adapter = ProductsAdapter(results) { resultsModel ->
            onItemSelected(
                resultsModel
            )
        }
    }

    private fun onItemSelected(resultsModel: ResultsModel) {
        val intent = Intent(this, DetailProduct::class.java)
        intent.putExtra("resultsModel", resultsModel)
        startForResult.launch(intent)
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
                    parent: AdapterView<*>?, view: View?, position: Int, id: Long
                ) {
                    val optionSelected = parent!!.getItemAtPosition(position).toString()
                    if (optionSelected.isNotEmpty()) {
                        dialog!!.dismiss()
                        siteSelected = optionSelected
                        binding.svProducts.queryHint =
                            getSpecificString(siteSelected, parent.context, "msg_placeholder")
                        binding.titleSite.text = siteSelected
                        binding.svProducts.setQuery(binding.svProducts.query, true)
                        binding.svProducts.requestFocus()
                    }
                }

            }


        dialog!!.setCancelable(false)
        dialog!!.show()
    }
}