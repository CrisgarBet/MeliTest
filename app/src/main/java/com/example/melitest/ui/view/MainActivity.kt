package com.example.melitest.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.SearchView
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.melitest.data.enums.Site
import com.example.melitest.data.model.ResultsModel
import com.example.melitest.databinding.ActivityMainBinding
import com.example.melitest.ui.adapter.ProductsAdapter
import com.example.melitest.ui.viewmodel.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener,
    androidx.appcompat.widget.SearchView.OnQueryTextListener {

    private lateinit var binding: ActivityMainBinding

    private val searchViewModel: SearchViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        searchViewModel.onCreate()
        searchViewModel.searchModel.observe(this, Observer {
            val results = it?.results
            if (results != null) {
                loadResults(results)
            }
        })
        loadSpinnerSite()
        binding.svProducts.setOnQueryTextListener(this)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if (query != null) {
            searchProducts(query)
        };
        return true
    }

    override fun onQueryTextChange(query: String?): Boolean {
        return true
    }

    private fun searchProducts(search: String) {
        searchViewModel.searchProducts(binding.spSite.selectedItem.toString(), search)
    }

    private fun loadSpinnerSite() {

        val options = Site.values().map { it.name }

        val adapter = ArrayAdapter(
            this, android.R.layout.simple_spinner_item, options
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spSite.adapter = adapter
    }

    private fun loadResults(results: MutableList<ResultsModel>) {

        binding.rvProducts.setHasFixedSize(true)
        binding.rvProducts.layoutManager = LinearLayoutManager(this)
        binding.rvProducts.adapter = ProductsAdapter(results)
    }
}