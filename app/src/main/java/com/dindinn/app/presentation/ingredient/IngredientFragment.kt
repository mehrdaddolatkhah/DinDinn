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

        initTab()
        viewModel.getIngredientTabs()
        viewModel.getIngredient(1)
        observeOnLiveData()

        fragmentIngredientBinding.recyclerIngredinet.layoutManager =
            GridLayoutManager(requireContext(), 2)

        fragmentIngredientBinding.swipeRefresh.setOnRefreshListener {
            // todo : handle swipe to refresh and shimmer
            fragmentIngredientBinding.swipeRefresh.isRefreshing = false
        }

        return fragmentIngredientBinding.root
    }

    private fun initTab() {
        val tab1 = fragmentIngredientBinding.tabsIngredient.newTab()
        tab1.contentDescription = getString(R.string.text_ingredient_tab_bento)
        tab1.text = getString(R.string.text_ingredient_tab_bento)
        val tab2 = fragmentIngredientBinding.tabsIngredient.newTab()
        tab2.contentDescription = getString(R.string.text_ingredient_tab_main)
        tab2.text = getString(R.string.text_ingredient_tab_main)
        val tab3 = fragmentIngredientBinding.tabsIngredient.newTab()
        tab3.contentDescription = getString(R.string.text_ingredient_tab_appetizer)
        tab3.text = getString(R.string.text_ingredient_tab_appetizer)
        fragmentIngredientBinding.tabsIngredient.addTab(tab1)
        fragmentIngredientBinding.tabsIngredient.addTab(tab2)
        fragmentIngredientBinding.tabsIngredient.addTab(tab3)
        fragmentIngredientBinding.tabsIngredient.selectTab(tab1)
        fragmentIngredientBinding.tabsIngredient.addOnTabSelectedListener(this)
    }

    override fun onTabSelected(tab: TabLayout.Tab?) {
        when (tab?.position) {
            0 -> {
                viewModel.getIngredient(1)
            }
            1 -> {
                viewModel.getIngredient(2)
            }
            2 -> {
                viewModel.getIngredient(3)
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
    }

}