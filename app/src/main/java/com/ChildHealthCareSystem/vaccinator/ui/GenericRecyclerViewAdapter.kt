package com.softsolutions.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

class GenericRecyclerViewAdapter<T>(private val items: List<T>, private val layout: Int) :
    RecyclerView.Adapter<GenericRecyclerViewAdapter.GenericViewHolder>() {

    private var itemClickListener: OnListItemClickListener<T>? = null
    var onItemViewClick: OnItemViewClickListener<T>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenericViewHolder {
        val itemBinding: ViewDataBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), layout, parent, false
        )
        return GenericViewHolder(itemBinding)
    }

    override fun getItemCount(): Int = items.size

    fun getItem(position : Int) = items[position]

    override fun onBindViewHolder(holder: GenericViewHolder, position: Int) {
        val obj = items[position]
        holder.bind(obj)
        holder.binding.root.setOnClickListener {
            itemClickListener?.onItemClick(obj, position)
        }
//        holder.binding.setVariable(BR.onViewClick, onItemViewClick)
    }

    fun setItemClickListener(listener: OnListItemClickListener<T>?) {
        this.itemClickListener = listener
    }
    class GenericViewHolder(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
        fun <T> bind(obj: T) {
//            binding.setVariable(BR.obj, obj)
            binding.executePendingBindings()
        }

    }
}