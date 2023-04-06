package com.abduldev.shoppinglist.ui.shoppinglist

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.abduldev.shoppinglist.R
import com.abduldev.shoppinglist.adapter.ShoppingItemAdapter
import com.abduldev.shoppinglist.data.db.ShoppingDatabase
import com.abduldev.shoppinglist.data.db.entities.ShoppingItem
import com.abduldev.shoppinglist.data.repo.ShoppingRepo
import com.abduldev.shoppinglist.databinding.ActivityShoppingBinding
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import  org.kodein.di.generic.instance

class ShoppingActivity : AppCompatActivity(), KodeinAware {

    override val kodein by closestKodein()
    private val factory: ShoppingViewModelFactory by instance()

    private lateinit var binding: ActivityShoppingBinding

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityShoppingBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        val database = ShoppingDatabase(this)
//        val repo = ShoppingRepo(database)
//        val factory = ShoppingViewModelFactory(repo)

        val viewModel = ViewModelProvider(this, factory)[ShoppingViewModel::class.java]

        val adapter = ShoppingItemAdapter(listOf(), viewModel)

        with(binding) {
            rvShoppingItems.layoutManager = LinearLayoutManager(this@ShoppingActivity)
            rvShoppingItems.adapter = adapter

            fab.setOnClickListener {
                AddShoppingItemDialog(
                    this@ShoppingActivity,
                    object : DialogListener {
                        override fun onAddButtonClicked(item: ShoppingItem) {
                            viewModel.upsert(item)
                        }
                    }
                ).show()
            }
        }

        viewModel.getAllShoppingItems().observe(this) {
            adapter.items = it
            adapter.notifyDataSetChanged()
        }


    }


}

