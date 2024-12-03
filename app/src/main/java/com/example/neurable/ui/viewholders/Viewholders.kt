package com.example.neurable.ui.viewholders

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.neurable.R

class UserLayoutViewHolder1(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val userName: TextView = itemView.findViewById(R.id.title)
    val userEmail: TextView = itemView.findViewById(R.id.body)
}

class UserLayoutViewHolder2(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val userName: TextView = itemView.findViewById(R.id.title)
    val userEmail: TextView = itemView.findViewById(R.id.body)
}
