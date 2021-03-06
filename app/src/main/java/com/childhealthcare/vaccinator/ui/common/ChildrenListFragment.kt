package com.childhealthcare.vaccinator.ui.common

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.childhealthcare.vaccinator.databinding.FragmentChildrenListBinding
import com.childhealthcare.vaccinator.model.Child
import com.childhealthcare.vaccinator.ui.GenericRecyclerViewAdapter
import com.childhealthcare.vaccinator.ui.OnListItemClickListener
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
        binding.onChildClick = OnChildClick()

        mViewModel.searchText.observe(viewLifecycleOwner, {
            (binding.rvChildrenList.adapter as GenericRecyclerViewAdapter<*>).filter(it)
        })
    }

    inner class OnChildClick : OnListItemClickListener<Child> {
        override fun onItemClick(item: Child, pos: Int) {
            val pageIndex = if (isVaccine) VACCINATION_PAGE else POLIO_PAGE
            val action = ChildrenListFragmentDirections.actionDestChildrenToDestChild(item.id, pageIndex)
            binding.root.findNavController().navigate(action)
        }

    }

}