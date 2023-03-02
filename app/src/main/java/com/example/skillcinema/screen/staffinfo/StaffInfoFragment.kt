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
import com.bumptech.glide.Glide
import com.example.skillcinema.R
import com.example.skillcinema.databinding.FragmentStaffInfoBinding
import com.example.skillcinema.entity.*
import com.example.skillcinema.recyclerview.*
import com.example.skillcinema.recyclerview.staffinfo.StaffInfoAdapter
import com.example.skillcinema.screen.HomeFragment
import com.example.skillcinema.screen.allmovie.AllMoviesFragment
import com.example.skillcinema.screen.movieinfo.MovieInfoFragment
import kotlinx.coroutines.launch

class StaffInfoFragment : Fragment() {

    private var _binding: FragmentStaffInfoBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MoviePagedListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStaffInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonBackInfoStaff.setOnClickListener {
            loadFragment(MovieInfoFragment())
        }

        val urlMovie = "/api/v1/staff/$idPerson"

        viewLifecycleOwner.lifecycleScope.launch {

            val infoStaff = viewModel.infoStaff(urlMovie)

            val films = mutableListOf<FilmsStaff>()
            infoStaff.films.forEach{
                films.add(it)
            }
            val listFilms = mutableListOf<MovieInfo>()
            films.first {
                val urlMovie1 = "/api/v2.2/films/${it.filmId}"
                listFilms.add(viewModel.infoMovie(urlMovie1))
            }
            listFilms.sortByDescending { it.ratingKinopoisk }
            val staffAdapter = StaffInfoAdapter { onClickPhoto(it, it.kinopoiskId) }
            staffAdapter.setData(listFilms)


            Glide.with(this@StaffInfoFragment)
                .load(infoStaff.posterUrl)
                .into(binding.imageStaffInfo)
            binding.nameStaff.text = infoStaff.nameRu ?: infoStaff.nameEn ?: ""
            binding.roleStaff.text = infoStaff.profession

            binding.bestFilmsRecyclerView.adapter = staffAdapter
            binding.countFilmsStaff.text = "${films.size} ${countFilmsEnding(films.size)}"

            binding.textToList.setOnClickListener {
                loadFragment(FilmographyFragment())
            }
        }
    }

    private fun onClickPhoto(item: MovieInfo, id: Int) {
        idFilm = id
        loadFragment(MovieInfoFragment())
    }

    private fun loadFragment(fragment: Fragment) {
        val transaction = fragmentManager?.beginTransaction()
        transaction?.setCustomAnimations(R.anim.activity_in, R.anim.activity_out)
        transaction?.replace(R.id.container, fragment)
        transaction?.commit()
    }

    private fun countFilmsEnding(count: Int): String {
        val preLastDigit = count % 100 / 10
        return if (preLastDigit == 1) {
            "фильм"
        } else when (count % 10) {
            1 -> "фильм"
            2, 3, 4 -> "фильма"
            else -> "фильмов"
        }
    }
}