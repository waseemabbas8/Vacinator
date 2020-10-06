package com.childhealthcare.vaccinator.ui.common

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import com.childhealthcare.vaccinator.R
import com.childhealthcare.vaccinator.databinding.FragmentChildrenListBinding
import com.childhealthcare.vaccinator.model.Child
import com.childhealthcare.vaccinator.ui.GenericRecyclerViewAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf


class ChildrenListFragment : Fragment() {

    private lateinit var binding: FragmentChildrenListBinding
    private val mViewModel: ChildrenListViewModel by viewModel {
        parametersOf(isVaccine)
    }

    private var isVaccine = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            val args = ChildrenListFragmentArgs.fromBundle(it)
            isVaccine = args.isVaccine
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentChildrenListBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this

        binding.spMohallahs.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
                mViewModel.mohId = mViewModel.mohallahs.value?.get(position)?.id ?: 0

                mViewModel.getChildrenList()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {}

        }
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.viewModel = mViewModel

        mViewModel.searchText.observe(viewLifecycleOwner, {
            (binding.rvChildrenList.adapter as GenericRecyclerViewAdapter<*>).filter(it)
        })
    }

}