package com.childhealthcare.vaccinator.ui.common

import androidx.fragment.app.Fragment
import com.childhealthcare.vaccinator.ui.BasePagerAdapter

const val VACCINATION_PAGE = 0
const val POLIO_PAGE = 1

private val CHILD_TABS_TITLES = listOf("Vaccination", "Polio")

class ChildPagerAdapter(fragment: Fragment, private val viewModel: ChildViewModel) : BasePagerAdapter(fragment) {

    override val tabFragmentsCreators: Map<Int, () -> Fragment> = mapOf(
        VACCINATION_PAGE to { VaccinationsFragment(viewModel) },
        POLIO_PAGE to { PolioFragment(viewModel) }
    )

    override fun getTabTitle(position: Int): String = CHILD_TABS_TITLES[position]

}