package com.childhealthcare.vaccinator.ui

import android.view.View

 interface OnItemViewClickListener<T> {
    fun onClick(view : View, item: T)
}