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
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class IngredientFragment : BaseFragment() {

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

        return fragmentIngredientBinding.root
    }

}