package com.abduldev.shoppinglist.ui.shoppinglist

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatDialog
import com.abduldev.shoppinglist.data.db.entities.ShoppingItem
import com.abduldev.shoppinglist.databinding.DialogAddShoppingItemBinding

class AddShoppingItemDialog(context: Context, var dialogListener: DialogListener) :
    AppCompatDialog(context) {

    private lateinit var binding: DialogAddShoppingItemBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DialogAddShoppingItemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            tvAdd.setOnClickListener {
                val name = etName.text.toString()
                val amount = etAmount.text.toString()

                if (name.isBlank() || amount.isEmpty()) {
                    Toast.makeText(context, "Please enter something", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                val item = ShoppingItem(name, amount.toInt())
                dialogListener.onAddButtonClicked(item)
                dismiss()
            }

            tvCancel.setOnClickListener {
                cancel()
            }
        }

    }


}