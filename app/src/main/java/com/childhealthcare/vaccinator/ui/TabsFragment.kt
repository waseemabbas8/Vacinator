package com.childhealthcare.vaccinator.ui

import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.childhealthcare.vaccinator.ui.BasePagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

abstract class TabsFragment : Fragment() {

    protected fun initTabs(viewPager: ViewPager2, tabLayout: TabLayout, pagerAdapter: BasePagerAdapter) {
        viewPager.adapter = pagerAdapter

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = pagerAdapter.getTabTitle(position)
        }.attach()

        viewPager.offscreenPageLimit = pagerAdapter.itemCount
    }

}