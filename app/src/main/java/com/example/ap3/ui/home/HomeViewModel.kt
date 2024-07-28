package com.example.ap3.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ap3.adapter.Studio
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class HomeViewModel : ViewModel() {

    private val _studios = MutableLiveData<List<Studio>>()
    val studios: LiveData<List<Studio>> get() = _studios

    private val _filteredStudios = MediatorLiveData<List<Studio>>()
    val filteredStudios: LiveData<List<Studio>> get() = _filteredStudios

    private val searchQuery = MutableLiveData<String>()

    init {
        fetchStudios()
        setupSearch()
    }

    private fun fetchStudios() {
        val database = FirebaseDatabase.getInstance().getReference("Item")
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val studioList = mutableListOf<Studio>()
                for (data in snapshot.children) {
                    val studio = data.getValue(Studio::class.java)
                    if (studio != null) {
                        studioList.add(studio)
                    }
                }
                _studios.value = studioList
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle database error
            }
        })
    }

    private fun setupSearch() {
        _filteredStudios.addSource(_studios) { studios ->
            filterStudios(studios, searchQuery.value.orEmpty())
        }
        _filteredStudios.addSource(searchQuery) { query ->
            _studios.value?.let { studios ->
                filterStudios(studios, query.orEmpty())
            }
        }
    }

    private fun filterStudios(studios: List<Studio>, query: String) {
        _filteredStudios.value = studios.filter { studio ->
            studio.name.contains(query, ignoreCase = true) ||
                    studio.description.contains(query, ignoreCase = true)
        }
    }

    fun setSearchQuery(query: String) {
        searchQuery.value = query
    }
}
