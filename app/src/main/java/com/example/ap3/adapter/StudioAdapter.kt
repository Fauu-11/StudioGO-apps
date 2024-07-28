package com.example.ap3.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ap3.StudioDetail
import com.example.ap3.databinding.ItemStudioBinding

class StudioAdapter(
    private val context: Context,
    private var studios: List<Studio>
) : RecyclerView.Adapter<StudioAdapter.StudioViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudioViewHolder {
        val binding = ItemStudioBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StudioViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StudioViewHolder, position: Int) {
        val studio = studios[position]
        holder.bind(studio)
        holder.itemView.setOnClickListener {
            val intent = Intent(context, StudioDetail::class.java).apply {
                putExtra("studioName", studio.name)
                putExtra("picUrl", studio.picUrl)
                putExtra("studioLocation", studio.address)
                putExtra("studioPrice", studio.price)
                putExtra("description", studio.description)
                putExtra("rating", studio.rate)
                putStringArrayListExtra("studioFacilities", ArrayList(studio.facilities))
            }
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = studios.size

    fun updateMovies(studios: List<Studio>) {
        this.studios = studios
        notifyDataSetChanged()
    }

    class StudioViewHolder(private val binding: ItemStudioBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(studio: Studio) {
            Glide.with(binding.imageStudio.context)
                .load(studio.picUrl)
                .into(binding.imageStudio)
            binding.studioTitle.text = studio.name
            binding.studioLocation.text = studio.address
            binding.studioPrice.text = studio.price
            binding.ratingBar.rating = studio.rate
        }
    }
}
