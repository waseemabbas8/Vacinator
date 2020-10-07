package com.childhealthcare.vaccinator.utils

import java.text.SimpleDateFormat
import java.util.*


fun getCurrentDate(): String {
    val c = Calendar.getInstance().time
    val df = SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault())
    return df.format(c)
}