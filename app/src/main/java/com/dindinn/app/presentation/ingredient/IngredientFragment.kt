package com.dindinn.app.presentation.ingredient

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.dindinn.app.R
import com.dindinn.app.databinding.FragmentIngredientBinding
import com.dindinn.app.presentation.base.BaseFragment
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
                fragmentIngredientBinding.bentoContainer.visibility = View.VISIBLE
                fragmentIngredientBinding.mainContainer.visibility = View.GONE
                fragmentIngredientBinding.appetizerContainer.visibility = View.GONE

            }
            1 -> {
                fragmentIngredientBinding.bentoContainer.visibility = View.GONE
                fragmentIngredientBinding.mainContainer.visibility = View.VISIBLE
                fragmentIngredientBinding.appetizerContainer.visibility = View.GONE
            }
            2 -> {
                fragmentIngredientBinding.bentoContainer.visibility = View.GONE
                fragmentIngredientBinding.mainContainer.visibility = View.GONE
                fragmentIngredientBinding.appetizerContainer.visibility = View.VISIBLE
            }
        }
    }

    override fun onTabUnselected(tab: TabLayout.Tab?) {
    }

    override fun onTabReselected(tab: TabLayout.Tab?) {
    }

}