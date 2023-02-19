package com.example.skillcinema.screen.movieinfo

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
import com.example.skillcinema.databinding.FragmentMovieInfoBinding
import com.example.skillcinema.recyclerview.MoviePagedListViewModel
import com.example.skillcinema.recyclerview.idFilm
import com.example.skillcinema.recyclerview.infoFilm
import com.example.skillcinema.screen.HomeFragment
import com.example.skillcinema.screen.allmovie.AllMoviesFragment
import kotlinx.coroutines.launch

class MovieInfoFragment : Fragment() {

    private var _binding: FragmentMovieInfoBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MoviePagedListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonBack.setOnClickListener {
            loadFragment(AllMoviesFragment())
        }

        val url = "/api/v2.2/films/$idFilm"

        viewLifecycleOwner.lifecycleScope.launch {
            val infoMovie = viewModel.infoMovie(url)
            Glide.with(this@MovieInfoFragment)
                .load(infoMovie.posterUrl)
                .into(binding.imageView)
            binding.ratingOnPoster.text = infoMovie.ratingKinopoisk.toString()
            binding.nameOnPoster.text = infoMovie.nameRu.toString()
        }
    }
    private fun loadFragment(fragment: Fragment) {
        val transaction = fragmentManager?.beginTransaction()
        transaction?.replace(R.id.container, fragment)
        transaction?.commit()
    }
}