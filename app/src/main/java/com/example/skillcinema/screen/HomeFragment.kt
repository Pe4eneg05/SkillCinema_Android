package com.example.skillcinema.screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.skillcinema.*
import com.example.skillcinema.databinding.FragmentHomeBinding
import com.example.skillcinema.entity.FilmsPremier
import com.example.skillcinema.recyclerview.*
import com.example.skillcinema.recyclerview.premier.PremierAdapter
import com.example.skillcinema.recyclerview.premier.PremierPagedAdapter
import com.example.skillcinema.screen.allmovie.AllMoviesFragment
import com.example.skillcinema.screen.movieinfo.MovieInfoFragment

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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
        transaction?.replace(R.id.container, fragment)
        transaction?.commit()
    }

    private fun onClickPhoto(item: FilmsPremier, id: Int){
        idFilm = id
        loadFragment(MovieInfoFragment())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}