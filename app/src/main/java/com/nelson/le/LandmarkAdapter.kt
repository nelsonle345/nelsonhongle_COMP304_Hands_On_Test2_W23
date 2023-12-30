package com.nelson.le
//Nelson Le - 301064831

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView



class LandmarkAdapter(private val landmarkTypes: List<String>, private val listener: OnItemClickListener) :
    RecyclerView.Adapter<LandmarkAdapter.LandmarkViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LandmarkViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_landmark_type, parent, false)
        return LandmarkViewHolder(view, listener)
    }
    // Add this method to get the landmark type based on position
    fun getLandmarkType(position: Int): String {
        return landmarkTypes[position]
    }

    override fun onBindViewHolder(holder: LandmarkViewHolder, position: Int) {
        holder.bind(landmarkTypes[position])
    }

    override fun getItemCount(): Int {
        return landmarkTypes.size
    }

    inner class LandmarkViewHolder(itemView: View, private val listener: OnItemClickListener) :
        RecyclerView.ViewHolder(itemView) {

        private val textViewLandmarkType: TextView = itemView.findViewById(R.id.textViewLandmarkType)

        fun bind(landmarkType: String) {
            textViewLandmarkType.text = landmarkType
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }
}