package com.childhealthcare.vaccinator.ui

interface OnListItemClickListener <T>{
    fun onItemClick(item: T, pos: Int)
}