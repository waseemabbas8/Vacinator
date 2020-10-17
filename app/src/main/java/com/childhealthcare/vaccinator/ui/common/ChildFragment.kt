package com.childhealthcare.vaccinator.ui.common

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.childhealthcare.vaccinator.R
import com.childhealthcare.vaccinator.data.RESPONSE_CODE_ERROR
import com.childhealthcare.vaccinator.databinding.FragmentChildBinding
import com.childhealthcare.vaccinator.ui.TabsFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf


class ChildFragment : TabsFragment() {

    private lateinit var binding: FragmentChildBinding
    private val mViewModel: ChildViewModel by viewModel{
        parametersOf(childId)
    }

    private var childId = 0
    private var pageIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            val args = ChildFragmentArgs.fromBundle(it)
            childId = args.childId
            pageIndex = args.pageIndex
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentChildBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this

        lifecycleScope.launch(Dispatchers.Main) {
            delay(200)
            binding.viewPager.currentItem = pageIndex
        }

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.viewModel = mViewModel

        initTabs(binding.viewPager, binding.tabs, ChildPagerAdapter(this, mViewModel))

        mViewModel.generalResponse.observe(viewLifecycleOwner, {
            if (it == null) return@observe
            if (it.code == RESPONSE_CODE_ERROR)
                Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
        })

    }

}