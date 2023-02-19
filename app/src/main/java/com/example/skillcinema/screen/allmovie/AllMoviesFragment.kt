package com.example.skillcinema.screen.allmovie

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.skillcinema.R
import com.example.skillcinema.databinding.FragmentAllMoviesBinding
import com.example.skillcinema.entity.FilmsPremier
import com.example.skillcinema.entity.FilmsSerDramDet
import com.example.skillcinema.entity.FilmsTopPopular
import com.example.skillcinema.recyclerview.*
import com.example.skillcinema.recyclerview.detectives.DetectivesPagedAdapter
import com.example.skillcinema.recyclerview.dram.DramPagedAdapter
import com.example.skillcinema.recyclerview.popular.PopularPagedAdapter
import com.example.skillcinema.recyclerview.premier.PremierPagedAdapter
import com.example.skillcinema.recyclerview.ser.SerPagedAdapter
import com.example.skillcinema.recyclerview.top.TopPagedAdapter
import com.example.skillcinema.screen.HomeFragment
import com.example.skillcinema.screen.movieinfo.MovieInfoFragment
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class AllMoviesFragment : Fragment() {

    private var _binding: FragmentAllMoviesBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MoviePagedListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAllMoviesBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerViewAll.layoutManager =
            GridLayoutManager(context, 2, LinearLayoutManager.VERTICAL, false)

        binding.buttonBack.setOnClickListener {
            loadFragment(HomeFragment())
        }

        binding.titleAll.text = typeAllFilms

        pagedAdapterPremier =  PremierPagedAdapter { onClickPhotoPremier(it, it.kinopoiskId) }
        pagedAdapterPopular =  PopularPagedAdapter { onClickPhotoTopPopular(it, it.filmId) }
        pagedAdapterDetectives = DetectivesPagedAdapter { onClickPhotoSerDramDet(it, it.kinopoiskId) }
        pagedAdapterTop =  TopPagedAdapter { onClickPhotoTopPopular(it, it.filmId) }
        pagedAdapterDram =  DramPagedAdapter { onClickPhotoSerDramDet(it, it.kinopoiskId) }
        pagedAdapterSer =  SerPagedAdapter { onClickPhotoSerDramDet(it, it.kinopoiskId) }

        viewModel.pagedPopular.onEach {
            pagedAdapterPopular.submitData(it)
        }.launchIn(lifecycleScope)

        viewModel.filmsPremier.onEach {
            pagedAdapterPremier.setData(it)
        }.launchIn(lifecycleScope)

        viewModel.pagedDetectives.onEach {
            pagedAdapterDetectives.submitData(it)
        }.launchIn(lifecycleScope)

        viewModel.pagedTop.onEach {
            pagedAdapterTop.submitData(it)
        }.launchIn(lifecycleScope)

        viewModel.pagedDram.onEach {
            pagedAdapterDram.submitData(it)
        }.launchIn(lifecycleScope)

        viewModel.pagedSer.onEach {
            pagedAdapterSer.submitData(it)
        }.launchIn(lifecycleScope)

        when (binding.titleAll.text) {

            "Премьеры" -> binding.recyclerViewAll.adapter = pagedAdapterPremier

            "Популярное" -> binding.recyclerViewAll.adapter = pagedAdapterPopular

            "Российские детективы" -> binding.recyclerViewAll.adapter = pagedAdapterDetectives

            "Топ-250" -> binding.recyclerViewAll.adapter = pagedAdapterTop

            "Советские драмы" -> binding.recyclerViewAll.adapter = pagedAdapterDram

            "Сериалы" -> binding.recyclerViewAll.adapter = pagedAdapterSer
        }
    }

    private fun onClickPhotoPremier(item: FilmsPremier, id: Int){
        idFilm = id
        loadFragment(MovieInfoFragment())
    }
    private fun onClickPhotoSerDramDet(item: FilmsSerDramDet, id: Int){
        idFilm = id
        loadFragment(MovieInfoFragment())
    }
    private fun onClickPhotoTopPopular(item: FilmsTopPopular, id: Int){
        idFilm = id
        loadFragment(MovieInfoFragment())
    }

    private fun loadFragment(fragment: Fragment) {
        val transaction = fragmentManager?.beginTransaction()
        transaction?.replace(R.id.container, fragment)
        transaction?.commit()
    }
}