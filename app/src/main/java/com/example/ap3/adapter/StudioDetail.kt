package com.example.ap3

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ap3.adapter.StudioFacilitiesAdapter

class StudioDetail : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_studio_detail)

        val studioName = intent.getStringExtra("studioName")
        val picUrl = intent.getStringExtra("picUrl")
        val studioLocation = intent.getStringExtra("studioLocation")
        val studioPrice = intent.getStringExtra("studioPrice")
        val studioDescription = intent.getStringExtra("description")
        val studioRating = intent.getFloatExtra("rating", 0.0f)
        val studioFacilities = intent.getStringArrayListExtra("studioFacilities") ?: listOf<String>()

        val studioImage = findViewById<ImageView>(R.id.studio_image)
        val studioNameView = findViewById<TextView>(R.id.studio_name)
        val studioLocationView = findViewById<TextView>(R.id.studio_location)
        val studioPriceView = findViewById<TextView>(R.id.studio_price)
        val studioDescriptionView = findViewById<TextView>(R.id.studio_description)
        val studioRatingBar = findViewById<RatingBar>(R.id.studio_rating)
        val studioFacilitiesRecycler = findViewById<RecyclerView>(R.id.studio_facilities_recycler)
        val bookStudioButton = findViewById<Button>(R.id.book_studio_button)

        Glide.with(this)
            .load(picUrl)
            .into(studioImage)
        studioNameView.text = studioName
        studioLocationView.text = studioLocation
        studioPriceView.text = studioPrice
        studioDescriptionView.text = studioDescription
        studioRatingBar.rating = studioRating

        // Set up RecyclerView for studio facilities
        studioFacilitiesRecycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        studioFacilitiesRecycler.adapter = StudioFacilitiesAdapter(studioFacilities)

        bookStudioButton.setOnClickListener {
            // Handle booking logic here
        }
    }
}
