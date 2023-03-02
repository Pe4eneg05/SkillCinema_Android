package com.example.skillcinema.screen

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.skillcinema.R
import com.example.skillcinema.databinding.FragmentHomeBinding
import com.example.skillcinema.databinding.FragmentSearchBinding
import com.example.skillcinema.recyclerview.MoviePagedListViewModel
import com.example.skillcinema.recyclerview.allfilms.AllFilmsPagedAdapter
import com.example.skillcinema.recyclerview.allfilms.AllFilmsPagedSource
import com.example.skillcinema.recyclerview.ser.SerAdapter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MoviePagedListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val allFilmsAdapter = AllFilmsPagedAdapter()

        viewModel.pagedAllFilms.onEach {
            allFilmsAdapter.submitData(it)
        }.launchIn(lifecycleScope)

        binding.recyclerViewSearch.adapter = allFilmsAdapter

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}