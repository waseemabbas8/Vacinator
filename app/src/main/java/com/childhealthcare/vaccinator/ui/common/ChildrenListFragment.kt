package com.childhealthcare.vaccinator.ui.common

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.childhealthcare.vaccinator.R
import com.childhealthcare.vaccinator.databinding.FragmentChildrenListBinding
import com.childhealthcare.vaccinator.model.Child
import com.childhealthcare.vaccinator.ui.GenericRecyclerViewAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel


class ChildrenListFragment : Fragment() {

    private lateinit var binding: FragmentChildrenListBinding
    private val mViewModel: ChildrenListViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentChildrenListBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.viewModel = mViewModel

        mViewModel.searchText.observe(viewLifecycleOwner, {
            if (it.isNullOrEmpty()) return@observe
            (binding.rvChildrenList.adapter as GenericRecyclerViewAdapter<*>).filter(it)
        })
    }

}