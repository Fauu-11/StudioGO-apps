package com.example.ap3.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ap3.R
import com.example.ap3.adapter.Studio
import com.example.ap3.adapter.StudioAdapter
import com.example.ap3.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var newStudioAdapter: StudioAdapter
    private lateinit var hotDealsAdapter: StudioAdapter

    private val spacingInPixels by lazy {
        resources.getDimensionPixelSize(R.dimen.spacing)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        displayUsername()
        setupRecyclerViews()
        observeData()
        setupSearchView()
    }

    private fun displayUsername() {
        val sharedPreferences = requireContext().getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
        val email = sharedPreferences.getString("username", "User") ?: "User"
        val username = email.substringBefore("@")
        binding.username.text = "Welcome, ${username.toUpperCase()}"
    }

    private fun setupRecyclerViews() {
        newStudioAdapter = StudioAdapter(requireContext(), emptyList())
        hotDealsAdapter = StudioAdapter(requireContext(), emptyList())

        binding.movieList.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = newStudioAdapter
            addItemDecoration(SpacingItemDecoration(spacingInPixels))
        }

        binding.upcomingMovie.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = hotDealsAdapter
            addItemDecoration(SpacingItemDecoration(spacingInPixels))
        }
    }

    private fun observeData() {
        viewModel.filteredStudios.observe(viewLifecycleOwner, Observer { studios ->
            val newStudios = studios.filter { it.type == "new" }
            val hotDeals = studios.filter { it.type == "hot" }
            newStudioAdapter.updateMovies(newStudios)
            hotDealsAdapter.updateMovies(hotDeals)
        })
    }

    private fun setupSearchView() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.setSearchQuery(query.orEmpty())
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.setSearchQuery(newText.orEmpty())
                return true
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
