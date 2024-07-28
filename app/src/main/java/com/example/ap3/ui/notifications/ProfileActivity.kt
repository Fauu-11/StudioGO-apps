package com.example.ap3.ui.notifications

import android.os.Bundle
import android.widget.GridView
import androidx.appcompat.app.AppCompatActivity
import com.example.ap3.R

class ProfileActivity : AppCompatActivity() {

    private lateinit var gridView: GridView
    private lateinit var profileAdapter: ProfileAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        // Setup GridView
        gridView = findViewById(R.id.grid_view_profiles)
        gridView.numColumns = 2 // Set jumlah kolom di GridView (dalam contoh ini 2 kolom)

        // Example data for profiles
        val profiles = listOf(
            Profile(R.drawable.profile_image1, "Muhammad Fauzi Abdul Aziz", "\t211111040"),
            Profile(R.drawable.profile_image2, "Fadhlan Faidh", "\t211111019"),
            Profile(R.drawable.profile_image3, "Ade Yosi Susanto", "211111015"),
            Profile(R.drawable.profile_image4, "Bill Abrahams Ririhena", "211111008")
            // Add more profiles as needed
        )

        // Initialize adapter and set it to GridView
        profileAdapter = ProfileAdapter(this, profiles)
        gridView.adapter = profileAdapter
    }
}
