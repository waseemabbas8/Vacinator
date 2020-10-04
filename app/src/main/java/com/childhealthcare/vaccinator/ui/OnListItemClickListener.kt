package com.softsolutions.ui

interface OnListItemClickListener <T>{
    fun onItemClick(item: T, pos: Int)
}