package com.abduldev.shoppinglist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.abduldev.shoppinglist.R
import com.abduldev.shoppinglist.data.db.entities.ShoppingItem
import com.abduldev.shoppinglist.databinding.ShoppingItemBinding
import com.abduldev.shoppinglist.ui.shoppinglist.ShoppingViewModel

class ShoppingItemAdapter(
    var items: List<ShoppingItem>,
    private val viewModel: ShoppingViewModel
) : RecyclerView.Adapter<ShoppingItemAdapter.ShoppingViewHolder>() {


    inner class ShoppingViewHolder(itemBinding: ShoppingItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingViewHolder {
        val binding = (
                ShoppingItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent, false
                ))
        return ShoppingViewHolder(binding)
    }


    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ShoppingViewHolder, position: Int) {
        val currentShoppingItem = items[position]

        holder.itemView.findViewById<TextView>(
            R.id.tvName
        ).text = currentShoppingItem.name

        holder.itemView.findViewById<TextView>(
            R.id.tvAmount
        ).text = currentShoppingItem.amount.toString()

        holder.itemView.findViewById<ImageView>(
            R.id.ivDelete
        ).setOnClickListener {
            viewModel.delete(currentShoppingItem)
        }


        holder.itemView.findViewById<ImageView>(
            R.id.ivPlus
        ).setOnClickListener {
            currentShoppingItem.amount++
            viewModel.upsert(currentShoppingItem)
        }

        holder.itemView.findViewById<ImageView>(
            R.id.ivMinus
        ).setOnClickListener {
            if (currentShoppingItem.amount > 0)
                currentShoppingItem.amount--
            viewModel.upsert(currentShoppingItem)
        }
    }
}