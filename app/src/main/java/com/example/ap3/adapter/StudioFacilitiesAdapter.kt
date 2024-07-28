package com.example.ap3.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ap3.R

class StudioFacilitiesAdapter(private val facilities: List<String>) :
    RecyclerView.Adapter<StudioFacilitiesAdapter.FacilityViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FacilityViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_facility, parent, false)
        return FacilityViewHolder(view)
    }

    override fun onBindViewHolder(holder: FacilityViewHolder, position: Int) {
        holder.bind(facilities[position])
    }

    override fun getItemCount() = facilities.size

    class FacilityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val facilityTextView: TextView = itemView.findViewById(R.id.facility_name)

        fun bind(facility: String) {
            facilityTextView.text = facility
        }
    }
}
