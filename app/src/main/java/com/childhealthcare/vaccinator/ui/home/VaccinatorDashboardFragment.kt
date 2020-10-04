package com.childhealthcare.vaccinator.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.childhealthcare.vaccinator.databinding.FragmentVaccinatorDashboardBinding
import com.childhealthcare.vaccinator.model.GridMenu
import com.childhealthcare.vaccinator.ui.OnListItemClickListener
import org.koin.androidx.viewmodel.ext.android.viewModel

private const val PROFILE_INDEX = 0
private const val VACCINATION_INDEX = 1
private const val POLIO_INDEX = 2


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
        binding.onItemClick = OnDashboardItemClick()

    }

    inner class OnDashboardItemClick : OnListItemClickListener<GridMenu> {
        override fun onItemClick(item: GridMenu, pos: Int) {
            when(pos) {
                PROFILE_INDEX -> {
                    val action = VaccinatorDashboardFragmentDirections.actionDestVaccinatorDashboardToDestProfile()
                    binding.root.findNavController().navigate(action)
                }
                VACCINATION_INDEX -> {
                    val action = VaccinatorDashboardFragmentDirections.actionDestVaccinatorDashboardToDestChildren()
                    binding.root.findNavController().navigate(action)
                }
            }
        }

    }
}