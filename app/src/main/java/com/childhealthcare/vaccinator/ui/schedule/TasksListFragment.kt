package com.childhealthcare.vaccinator.ui.schedule

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.childhealthcare.vaccinator.data.RESPONSE_CODE_ERROR
import com.childhealthcare.vaccinator.databinding.FragmentTasksListBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class TasksListFragment : Fragment() {

    private lateinit var binding: FragmentTasksListBinding
    private val mViewModel: TasksListViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTasksListBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this

        binding.addNewTask.setOnClickListener {
            val action = TasksListFragmentDirections.actionDestTasksListToDestAddTask()
            binding.root.findNavController().navigate(action)
        }

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.viewModel = mViewModel

        mViewModel.getTasksList()

        mViewModel.generalResponse.observe(viewLifecycleOwner, {
            if (it == null) return@observe
            if (it.code == RESPONSE_CODE_ERROR)
                Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
        })

    }

}