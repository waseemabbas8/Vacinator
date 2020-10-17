package com.childhealthcare.vaccinator.ui.message

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import com.childhealthcare.vaccinator.R
import com.childhealthcare.vaccinator.databinding.FragmentMessageBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MessageFragment : Fragment() {

    private lateinit var binding: FragmentMessageBinding
    private val mViewModel: MessageViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMessageBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this

        binding.rvContacts.isNestedScrollingEnabled = false

        binding.spMohallahs.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, view: View?, position: Int, p3: Long) {
                mViewModel.getParentsList(mViewModel.mohallahs.value?.get(position)?.id ?: 0)
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {}

        }

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.viewModel = mViewModel
    }

}