package com.example.adaptivetestapplication

import androidx.recyclerview.widget.DiffUtil

class DiffCallback: DiffUtil.ItemCallback<String>() {
    override fun areItemsTheSame(oldItem: String, newItem: String) = oldItem == newItem
    override fun areContentsTheSame(oldItem: String, newItem: String) = areItemsTheSame(oldItem, newItem)
}