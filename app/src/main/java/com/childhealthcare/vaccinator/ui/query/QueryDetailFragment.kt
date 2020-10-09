package com.childhealthcare.vaccinator.ui.query

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.childhealthcare.vaccinator.databinding.FragmentQueryDetailBinding
import com.childhealthcare.vaccinator.model.QueryModel


class QueryDetailFragment : Fragment() {

    private lateinit var binding: FragmentQueryDetailBinding

    private var query: QueryModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            val args = QueryDetailFragmentArgs.fromBundle(it)
            query = args.query
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentQueryDetailBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this

        binding.obj = query

        return binding.root
    }

}