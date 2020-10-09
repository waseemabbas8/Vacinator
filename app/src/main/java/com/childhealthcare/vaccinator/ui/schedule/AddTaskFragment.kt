package com.childhealthcare.vaccinator.ui.schedule

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.childhealthcare.vaccinator.data.RESPONSE_CODE_OK
import com.childhealthcare.vaccinator.databinding.AddTaskFragmentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddTaskFragment : Fragment() {

    private lateinit var binding: AddTaskFragmentBinding
    private val mViewModel: AddTaskViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = AddTaskFragmentBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this



        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.viewModel = mViewModel

        mViewModel.generalResponse.observe(viewLifecycleOwner, {
            if (it == null) return@observe
            Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
            if (it.code == RESPONSE_CODE_OK)
                activity?.onBackPressed()
        })
    }

}