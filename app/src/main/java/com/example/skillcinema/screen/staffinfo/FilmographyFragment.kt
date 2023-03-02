package com.example.skillcinema.screen.staffinfo

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.skillcinema.R
import com.example.skillcinema.databinding.FragmentFilmographyBinding
import com.example.skillcinema.entity.*
import com.example.skillcinema.recyclerview.*
import com.example.skillcinema.recyclerview.staffinfo.filmography.FilmographyAdapter
import com.example.skillcinema.screen.movieinfo.MovieInfoFragment
import kotlinx.coroutines.launch

class FilmographyFragment : Fragment() {

    private var _binding: FragmentFilmographyBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MoviePagedListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFilmographyBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val urlMovie = "/api/v1/staff/$idPerson"

        viewLifecycleOwner.lifecycleScope.launch {

            val infoStaff = viewModel.infoStaff(urlMovie)

            val films = mutableListOf<FilmsStaff>()
            infoStaff.films.first {
                films.add(it)
            }
            val listFilms = mutableListOf<MovieInfo>()
            films.first {
                val urlMovie1 = "/api/v2.2/films/${it.filmId}"
                listFilms.add(viewModel.infoMovie(urlMovie1))
            }

            val staffAdapter = FilmographyAdapter()
            staffAdapter.setData(listFilms.shuffled())

            binding.recyclerViewFilmography.adapter = staffAdapter
        }
    }

    private fun onClickPhotoPremier(item: FilmsPremier, id: Int) {
        idFilm = id
        loadFragment(MovieInfoFragment())
    }

    private fun onClickPhotoSerDramDet(item: FilmsSerDramDet, id: Int) {
        idFilm = id
        loadFragment(MovieInfoFragment())
    }

    private fun onClickPhotoTopPopular(item: FilmsTopPopular, id: Int) {
        idFilm = id
        loadFragment(MovieInfoFragment())
    }

    private fun loadFragment(fragment: Fragment) {
        val transaction = fragmentManager?.beginTransaction()
        transaction?.setCustomAnimations(R.anim.activity_in, R.anim.activity_out)
        transaction?.replace(R.id.container, fragment)
        transaction?.commit()
    }
}