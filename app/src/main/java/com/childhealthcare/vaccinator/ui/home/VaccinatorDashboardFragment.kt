package com.childhealthcare.vaccinator.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.childhealthcare.vaccinator.databinding.FragmentVaccinatorDashboardBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class VaccinatorDashboardFragment : Fragment() {

    private lateinit var binding: FragmentVaccinatorDashboardBinding

    private val mViewModel: VacinatorDashboardViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentVaccinatorDashboardBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.viewModel = mViewModel

    }
}