package com.example.shoppinglist.ui.views.activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppinglist.R
import com.example.shoppinglist.data.database.entities.ShoppingItem
import com.example.shoppinglist.data.repositories.ShoppingRepository
import com.example.shoppinglist.databinding.ActivityMainBinding
import com.example.shoppinglist.ui.adapters.ShoppingListAdapter
import com.example.shoppinglist.ui.viewModels.ShoppingItemViewModel
import com.example.shoppinglist.ui.views.fragments.AddingItemDialogFragment
import com.example.shoppinglist.ui.views.fragments.OnItemClicked
import com.google.android.material.floatingactionbutton.FloatingActionButton
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    @Inject
    lateinit var shoppingRepository: ShoppingRepository

    @Inject
    lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var adapter: ShoppingListAdapter

    private lateinit var recyclerView: RecyclerView
    private lateinit var addBtn: FloatingActionButton
    private lateinit var list : List<ShoppingItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setSupportActionBar(binding.myToolbar)
        binding.myToolbar.setTitle(R.string.shopping_list)
        recyclerView = binding.recyclerView
        addBtn = binding.fab

        val viewModel: ShoppingItemViewModel by viewModels {
            ShoppingItemViewModel.Factory(shoppingRepository)
        }

//        val list = listOf(ShoppingItem("Apple", 2),
//            ShoppingItem("Apple", 2),
//            ShoppingItem("Banana", 4),
//            ShoppingItem("Papaya", 1),
//            ShoppingItem("WaterMelon",3),
//            ShoppingItem("Melon",5))

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        CoroutineScope(Dispatchers.IO).launch {
            list = viewModel.getAllItems()
            adapter.updateDate(list)
        }

        addBtn.setOnClickListener {
           AddingItemDialogFragment(object : OnItemClicked {
                override fun onItemClicked(item: ShoppingItem) {
                    viewModel.insertItem(item)
                    adapter.addItemToTheList(item)
                    Toast.makeText(this@HomeActivity,
                        "Your item has been added",
                        Toast.LENGTH_SHORT).show()


                }
            }).show(supportFragmentManager, "ADD ITEM DIALOG")
        }


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.home_app_bar_actions_layout,menu)
        return true
    }
}