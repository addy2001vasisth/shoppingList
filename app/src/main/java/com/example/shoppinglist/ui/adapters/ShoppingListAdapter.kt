package com.example.shoppinglist.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.ViewGroup.MarginLayoutParams
import androidx.core.view.setMargins
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppinglist.data.database.entities.ShoppingItem
import com.example.shoppinglist.data.repositories.ShoppingRepository
import com.example.shoppinglist.databinding.ShoppingItemBinding
import com.example.shoppinglist.utils.Utils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class ShoppingListAdapter @Inject constructor(var shoppingRepository: ShoppingRepository) : RecyclerView.Adapter<ShoppingListAdapter.ShoppingListItemVH>() {

    private var list: MutableList<ShoppingItem> = mutableListOf()

    inner class ShoppingListItemVH(itemView: ShoppingItemBinding) : RecyclerView.ViewHolder(itemView.root){
        val itemName = itemView.name
        val itemCount = itemView.count
        val plusIc = itemView.increase
        val minusIc = itemView.decrease
        val deleteIc = itemView.deleteIc
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingListItemVH {
        val binding = ShoppingItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        val vH = ShoppingListItemVH(binding)
        var view = binding.root
        val marginLP = view.layoutParams as MarginLayoutParams
        marginLP.setMargins(Utils.convertPxToDp(16f).toInt())
        return vH
    }

    override fun onBindViewHolder(holder: ShoppingListItemVH, position: Int) {
        holder.itemName.text = list[position].name
        holder.itemCount.text = list[position].count.toString()

        holder.plusIc.setOnClickListener {
            holder.itemCount.text = (++(list[position].count)).toString()
            shoppingRepository.insertItem(list[position])
        }
        holder.minusIc.setOnClickListener {
            var count = list[position].count
            if(count > 0){
                count--
                holder.itemCount.text = (count).toString()
                list[position].count = count
                shoppingRepository.insertItem(list[position])
            }

        }
        holder.deleteIc.setOnClickListener {
            shoppingRepository.deleteItem(list[position])
            list.removeAt(position)
            updateDate(list)
        }
    }

    override fun getItemCount(): Int = list.size

    fun updateDate(list : List<ShoppingItem>){
        this.list = list.toMutableList()
        notifyDataSetChanged()
    }

    fun addItemToTheList(item: ShoppingItem) {
        list.add(item)
        notifyItemInserted(list.size)
    }
}