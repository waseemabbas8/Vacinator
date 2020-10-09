package com.childhealthcare.vaccinator.ui.query

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.childhealthcare.vaccinator.databinding.QueriesListFragmentBinding
import com.childhealthcare.vaccinator.model.QueryModel
import com.childhealthcare.vaccinator.ui.OnListItemClickListener
import org.koin.androidx.viewmodel.ext.android.viewModel

class QueriesListFragment : Fragment() {

    private lateinit var binding: QueriesListFragmentBinding
    private val mViewModel: QueriesListViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = QueriesListFragmentBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.viewModel = mViewModel
        binding.onItemClick = OnQueryItemClick()
    }

    inner class OnQueryItemClick : OnListItemClickListener<QueryModel> {
        override fun onItemClick(item: QueryModel, pos: Int) {
            val action = QueriesListFragmentDirections.actionDestQueriesListToDestQueryFetail(item)
            binding.root.findNavController().navigate(action)
        }

    }

}