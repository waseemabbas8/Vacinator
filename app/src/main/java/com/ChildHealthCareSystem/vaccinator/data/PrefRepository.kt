package com.ChildHealthCareSystem.vaccinator.data

import com.ChildHealthCareSystem.vaccinator.R
import com.ChildHealthCareSystem.vaccinator.model.GridMenu


class PrefRepository(){

    fun DashboardItems() : List<GridMenu> = listOf(
        GridMenu(R.string.Personal.toString(),R.drawable.ic_user),
        GridMenu(R.string.vaccination.toString(),R.drawable.ic_vaccine),
        GridMenu(R.string.polio.toString(),R.drawable.ic_polio),
        GridMenu(R.string.child.toString(),R.drawable.ic_child),
        GridMenu(R.string.job.toString(),R.drawable.ic_calendar),
        GridMenu(R.string.noti.toString(),R.drawable.ic_notifications)
    )
}