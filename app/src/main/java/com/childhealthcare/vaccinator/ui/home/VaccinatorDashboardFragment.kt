package com.childhealthcare.vaccinator.ui.home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import com.childhealthcare.vaccinator.LoginActivity
import com.childhealthcare.vaccinator.databinding.FragmentVaccinatorDashboardBinding
import com.childhealthcare.vaccinator.model.GridMenu
import com.childhealthcare.vaccinator.ui.OnListItemClickListener
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

private const val PROFILE_INDEX = 0
private const val VACCINATION_INDEX = 1
private const val POLIO_INDEX = 2
private const val SCHEDULE_INDEX = 3
private const val NTF_INDEX = 4


class VaccinatorDashboardFragment : Fragment() {

    private lateinit var binding: FragmentVaccinatorDashboardBinding

    private val mViewModel: VacinatorDashboardViewModel by viewModel()

    private var logoutClickCount = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentVaccinatorDashboardBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this

        binding.logout.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
                logoutClickCount++
                if (logoutClickCount == 1)
                    Toast.makeText(context, "Press again to logout", Toast.LENGTH_SHORT).show()
                else
                    logout()
                delay(2000)
                logoutClickCount = 0
            }
        }

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.viewModel = mViewModel
        binding.onItemClick = OnDashboardItemClick()

    }

    private fun logout() {
        mViewModel.logoutUser()
        val intent = Intent(context, LoginActivity::class.java)
        startActivity(intent)
        activity?.finish()
    }

    inner class OnDashboardItemClick : OnListItemClickListener<GridMenu> {
        override fun onItemClick(item: GridMenu, pos: Int) {
            when (pos) {
                PROFILE_INDEX -> {
                    val action =
                        VaccinatorDashboardFragmentDirections.actionDestVaccinatorDashboardToDestProfile()
                    binding.root.findNavController().navigate(action)
                }
                VACCINATION_INDEX -> {
                    val action =
                        VaccinatorDashboardFragmentDirections.actionDestVaccinatorDashboardToDestChildren(true)
                    binding.root.findNavController().navigate(action)
                }
                POLIO_INDEX -> {
                    val action =
                        VaccinatorDashboardFragmentDirections.actionDestVaccinatorDashboardToDestChildren(false)
                    binding.root.findNavController().navigate(action)
                }
                SCHEDULE_INDEX -> {
                    val action =
                        VaccinatorDashboardFragmentDirections.actionDestVaccinatorDashboardToDestTasksList()
                    binding.root.findNavController().navigate(action)
                }
                NTF_INDEX -> {
                    val action =
                        VaccinatorDashboardFragmentDirections.actionDestVaccinatorDashboardToDestQueriesList()
                    binding.root.findNavController().navigate(action)
                }
            }
        }

    }
}