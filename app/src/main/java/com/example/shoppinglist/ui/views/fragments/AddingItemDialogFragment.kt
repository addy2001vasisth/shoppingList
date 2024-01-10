package com.example.shoppinglist.ui.views.fragments

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.fragment.app.DialogFragment
import com.example.shoppinglist.data.database.entities.ShoppingItem
import com.example.shoppinglist.databinding.FragmentAddingItemBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddingItemDialogFragment(private val onItemClicked: OnItemClicked) : DialogFragment() {

    private lateinit var binding: FragmentAddingItemBinding
    private lateinit var closeBtn : AppCompatButton
    private lateinit var saveBtn : AppCompatButton
    private lateinit var itemNameEtBox : AppCompatEditText
    private lateinit var increaseBtn : ImageView
    private lateinit var decreaseBtn : ImageView
    private var itemCount = 1
    private lateinit var itemCounter : TextView


    override fun onStart() {
        super.onStart()

        val metrics = resources.displayMetrics
        val width = metrics.widthPixels
        dialog!!.window!!.setLayout((6 * width)/7, ViewGroup.LayoutParams.WRAP_CONTENT)
        dialog!!.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentAddingItemBinding.inflate(inflater,container,false)
        closeBtn = binding.closeBtn
        saveBtn = binding.saveBtn
        itemNameEtBox = binding.itemName
        increaseBtn = binding.increase
        decreaseBtn = binding.decrease
        itemCounter = binding.count

        itemCounter.text = itemCount.toString()

        disableClick(true)
        itemNameEtBox.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(itemCount == 0 || itemNameEtBox.text!!.isEmpty()){
                    disableClick(true)
                } else{
                    disableClick(false)
                }

            }

            override fun afterTextChanged(p0: Editable?) {
            }

        })

        increaseBtn.setOnClickListener {
            itemCount++
            itemCounter.text = itemCount.toString()
        }

        decreaseBtn. setOnClickListener {
            if(itemCount > 0){
                itemCount--
                itemCounter.text = itemCount.toString()
            }
        }

        closeBtn.setOnClickListener{
            this.dismiss()
        }

        saveBtn.setOnClickListener {
            onItemClicked.onItemClicked(ShoppingItem(itemNameEtBox.text.toString(),itemCount))
            this.dismiss()
        }
        return binding.root

    }

    fun disableClick(value: Boolean){
        saveBtn.isEnabled = !value
        saveBtn.isClickable = !value
        if(value){
            saveBtn.alpha = 0.4f
        } else{
            saveBtn.alpha = 1f
        }
    }

}

interface OnItemClicked{
    fun onItemClicked(item: ShoppingItem)
}