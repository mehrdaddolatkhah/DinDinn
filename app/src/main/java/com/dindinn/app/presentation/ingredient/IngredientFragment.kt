package com.dindinn.app.presentation.ingredient

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.dindinn.app.R
import com.dindinn.app.databinding.FragmentIngredientBinding
import com.dindinn.app.databinding.ListItemIngredientBinding
import com.dindinn.app.domain.model.FoodModel
import com.dindinn.app.presentation.base.BaseFragment
import com.dindinn.app.presentation.base.adapter.SingleLayoutAdapter
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class IngredientFragment : BaseFragment(), TabLayout.OnTabSelectedListener {

    private lateinit var fragmentIngredientBinding: FragmentIngredientBinding
    private val viewModel: IngredientViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentIngredientBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_ingredient, container, false)
        fragmentIngredientBinding.viewModel = viewModel

        viewModel.getIngredientTabs()
        observeOnLiveData()

        fragmentIngredientBinding.recyclerIngredinet.layoutManager =
            GridLayoutManager(requireContext(), 2)

        fragmentIngredientBinding.swipeRefresh.setOnRefreshListener {
            // todo : handle swipe to refresh and shimmer
            fragmentIngredientBinding.swipeRefresh.isRefreshing = false
        }

        return fragmentIngredientBinding.root
    }

    override fun onTabSelected(tab: TabLayout.Tab?) {

        tab?.position?.let { selectedTabPosition ->
            viewModel.ingredientTabList[selectedTabPosition].categoryId?.let { tabCategoryId ->
                viewModel.getIngredient(tabCategoryId)
            }
        }
    }

    override fun onTabUnselected(tab: TabLayout.Tab?) {
    }

    override fun onTabReselected(tab: TabLayout.Tab?) {
    }

    private fun observeOnLiveData() {

        viewModel.ingredientList.observe(viewLifecycleOwner, Observer { ingredient ->

            fragmentIngredientBinding.adapter =
                SingleLayoutAdapter<FoodModel, ListItemIngredientBinding>(
                    R.layout.list_item_ingredient,
                    ingredient.foodList,
                    viewModel,
                    onBind = {
                        Glide.with(requireContext())
                            .load(ingredient.foodList[it].picture)
                            .into(this.imgIngredientItem)
                    }
                )

        })

        viewModel.ingredientTabs.observe(viewLifecycleOwner, Observer { tabs ->
            tabs.tabs.forEach { tabDetails ->
                val tab = fragmentIngredientBinding.tabsIngredient.newTab()
                tab.contentDescription = tabDetails.tabTitle
                tab.text = tabDetails.tabTitle
                fragmentIngredientBinding.tabsIngredient.addTab(tab)
                viewModel.ingredientTabList.add(tabDetails)
            }
            fragmentIngredientBinding.tabsIngredient.addOnTabSelectedListener(this)
        })

        viewModel.shouldVisibleEmptyIngredientList.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                fragmentIngredientBinding.recyclerIngredinet.visibility = View.GONE
                fragmentIngredientBinding.txtIngredientEmpty.visibility = View.VISIBLE
            } else {
                fragmentIngredientBinding.recyclerIngredinet.visibility = View.VISIBLE
                fragmentIngredientBinding.txtIngredientEmpty.visibility = View.GONE
            }
        })
    }

}