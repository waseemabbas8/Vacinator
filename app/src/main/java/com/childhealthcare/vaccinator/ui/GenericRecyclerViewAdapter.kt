package com.childhealthcare.vaccinator.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.childhealthcare.vaccinator.BR
import java.util.*
import kotlin.collections.ArrayList

class GenericRecyclerViewAdapter<T>(private val items: List<T>, private val layout: Int) :
    RecyclerView.Adapter<GenericRecyclerViewAdapter.GenericViewHolder>() {

    private var itemClickListener: OnListItemClickListener<T>? = null
    var onItemViewClick: OnItemViewClickListener<T>? = null

    private val filteredItems = arrayListOf<T>()

    init {
        filteredItems.addAll(items)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenericViewHolder {
        val itemBinding: ViewDataBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), layout, parent, false
        )
        return GenericViewHolder(itemBinding)
    }

    override fun getItemCount(): Int = filteredItems.size

    override fun onBindViewHolder(holder: GenericViewHolder, position: Int) {
        val obj = filteredItems[position]
        holder.bind(obj)
        holder.binding.root.setOnClickListener {
            itemClickListener?.onItemClick(obj, position)
        }
//        holder.binding.setVariable(BR.onViewClick, onItemViewClick)
    }

    fun setItemClickListener(listener: OnListItemClickListener<T>?) {
        this.itemClickListener = listener
    }

    fun filter(charStr: String) {
        val charText = charStr.toLowerCase(Locale.getDefault())
        filteredItems.clear()
        if (charText.isEmpty()) {
            filteredItems.addAll(items)
        } else {
            for (item in items) {
                if (item.toString().toLowerCase(Locale.getDefault()).contains(charText)) {
                    filteredItems.add(item)
                }
            }
        }
        notifyDataSetChanged()
    }

    class GenericViewHolder(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
        fun <T> bind(obj: T) {
            binding.setVariable(BR.obj, obj)
            binding.executePendingBindings()
        }

    }
}