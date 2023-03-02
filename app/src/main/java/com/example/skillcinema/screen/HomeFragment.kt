package com.example.skillcinema.screen

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.skillcinema.*
import com.example.skillcinema.databinding.FragmentHomeBinding
import com.example.skillcinema.entity.FilmsPremier
import com.example.skillcinema.entity.FilmsSerDramDet
import com.example.skillcinema.entity.FilmsTopPopular
import com.example.skillcinema.recyclerview.*
import com.example.skillcinema.recyclerview.detectives.DetectivesAdapter
import com.example.skillcinema.recyclerview.dram.DramAdapter
import com.example.skillcinema.recyclerview.popular.PopularAdapter
import com.example.skillcinema.recyclerview.premier.PremierAdapter
import com.example.skillcinema.recyclerview.ser.SerAdapter
import com.example.skillcinema.recyclerview.top.TopAdapter
import com.example.skillcinema.screen.allmovie.AllMoviesFragment
import com.example.skillcinema.screen.movieinfo.MovieInfoFragment
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MoviePagedListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val premierAdapter = PremierAdapter { onClickPhotoPremier(it, it.kinopoiskId) }
        val popularAdapter = PopularAdapter { onClickPhotoTopPopular(it, it.filmId) }
        val detectivesAdapter = DetectivesAdapter { onClickPhotoSerDramDet(it, it.kinopoiskId) }
        val topAdapter = TopAdapter { onClickPhotoTopPopular(it, it.filmId) }
        val dramAdapter = DramAdapter { onClickPhotoSerDramDet(it, it.kinopoiskId) }
        val serAdapter = SerAdapter { onClickPhotoSerDramDet(it, it.kinopoiskId) }

        viewModel.filmsTop.onEach {
            topAdapter.setData(it)
        }.launchIn(lifecycleScope)

        viewModel.filmsPremier.onEach {
            premierAdapter.setData(it)
        }.launchIn(lifecycleScope)

        viewModel.filmsSer.onEach {
            serAdapter.setData(it)
        }.launchIn(lifecycleScope)

        viewModel.filmsDram.onEach {
            dramAdapter.setData(it)
        }.launchIn(lifecycleScope)

        viewModel.filmsDet.onEach {
            detectivesAdapter.setData(it)
        }.launchIn(lifecycleScope)

        viewModel.filmsPopular.onEach {
            popularAdapter.setData(it)
        }.launchIn(lifecycleScope)

        binding.textAllPremier.setOnClickListener {
            loadFragment(AllMoviesFragment())
            typeAllFilms = "Премьеры"
        }

        binding.textAllPopular.setOnClickListener {
            loadFragment(AllMoviesFragment())
            typeAllFilms = "Популярное"
        }

        binding.textAllDetectives.setOnClickListener {
            loadFragment(AllMoviesFragment())
            typeAllFilms = "Российские детективы"
        }

        binding.textAllTop.setOnClickListener {
            loadFragment(AllMoviesFragment())
            typeAllFilms = "Топ-250"
        }

        binding.textAllDram.setOnClickListener {
            loadFragment(AllMoviesFragment())
            typeAllFilms = "Советские драмы"
        }

        binding.textAllSer.setOnClickListener {
            loadFragment(AllMoviesFragment())
            typeAllFilms = "Сериалы"
        }

        binding.recyclerViewPremier.adapter = premierAdapter
        binding.recyclerViewPopular.adapter = popularAdapter
        binding.recyclerViewDetectives.adapter = detectivesAdapter
        binding.recyclerViewTop.adapter = topAdapter
        binding.recyclerViewDram.adapter = dramAdapter
        binding.recyclerViewSer.adapter = serAdapter
    }

    private fun loadFragment(fragment: Fragment) {
        val transaction = fragmentManager?.beginTransaction()
        transaction?.setCustomAnimations(R.anim.activity_in, R.anim.activity_out)
        transaction?.replace(R.id.container, fragment)
        transaction?.addToBackStack(fragment.tag)
        transaction?.commit()
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}