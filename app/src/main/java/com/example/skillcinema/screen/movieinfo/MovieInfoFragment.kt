package com.example.skillcinema.screen.movieinfo

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
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.skillcinema.R
import com.example.skillcinema.databinding.FragmentMovieInfoBinding
import com.example.skillcinema.entity.ActorsList
import com.example.skillcinema.entity.FilmsTopPopular
import com.example.skillcinema.entity.SimilarItems
import com.example.skillcinema.recyclerview.*
import com.example.skillcinema.recyclerview.actor.ActorsAdapter
import com.example.skillcinema.recyclerview.directors.DirectorsAdapter
import com.example.skillcinema.recyclerview.image.ImagePagedAdapter
import com.example.skillcinema.recyclerview.similar.SimilarAdapter
import com.example.skillcinema.screen.HomeFragment
import com.example.skillcinema.screen.allmovie.AllMoviesFragment
import com.example.skillcinema.screen.staffinfo.AllStaffFragment
import com.example.skillcinema.screen.staffinfo.StaffInfoFragment
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
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

    @SuppressLint("SetTextI18n")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonBackInfoMovie.setOnClickListener {
            if (typeScreen) {
                loadFragment(HomeFragment())
            } else loadFragment(AllMoviesFragment())
        }

        val urlMovie = "/api/v2.2/films/$idFilm"
        val actorAdapter = ActorsAdapter ({ onClickStaff(it, it.staffId) }, true)
        val pagedAdapterImage = ImagePagedAdapter()
        val directorsAdapter = DirectorsAdapter ({ onClickStaff(it, it.staffId) }, true)
        val similarImage = SimilarAdapter { onClickPhoto(it, it.filmId) }

        viewModel.similar.onEach {
            similarImage.setData(it)
        }.launchIn(lifecycleScope)

        viewModel.actors.onEach {
            actorAdapter.setData(it)
        }.launchIn(lifecycleScope)

        viewModel.directors.onEach {
            directorsAdapter.setData(it)
        }.launchIn(lifecycleScope)

        viewLifecycleOwner.lifecycleScope.launch {
            var flagCountLines = true

            val infoMovie = viewModel.infoMovie(urlMovie)
            Glide.with(this@MovieInfoFragment)
                .load(infoMovie.posterUrl)
                .into(binding.posterInfoMovie)
            binding.nameInfoMovie.text = infoMovie.nameRu ?: ""
            binding.textInfoMovie.text = "${infoMovie.year}, ${infoMovie.genres.first().genre}"
            binding.text2InfoMovie.text =
                "${infoMovie.countries.first().country}, ${duration(infoMovie.filmLength)}"

            binding.textSloganInfoMovie.text = infoMovie.slogan

            binding.textDescriptionInfoMovie.text = infoMovie.description

            binding.textDescriptionInfoMovie.setOnClickListener {
                if (flagCountLines) {
                    binding.textDescriptionInfoMovie.maxLines = Int.MAX_VALUE
                    flagCountLines = false
                } else {
                    binding.textDescriptionInfoMovie.maxLines = 5
                    flagCountLines = true
                }
            }

            binding.actorsCountInfoMovie.text = countActors.toString()
            binding.actorsRecyclerView.layoutManager =
                GridLayoutManager(context, 4, RecyclerView.HORIZONTAL, false)
            //мямямя люблю Кирюфу
            binding.directorsCountInfoMovie.text = countDirectors.toString()
            binding.directorsRecyclerView.layoutManager =
                GridLayoutManager(context, 4, RecyclerView.HORIZONTAL, false)

            binding.actorsRecyclerView.adapter = actorAdapter
            binding.directorsRecyclerView.adapter = directorsAdapter

            binding.galleryRecyclerView.adapter = pagedAdapterImage
            binding.galleryCountInfoMovie.text = pagedAdapterImage.itemCount.toString()
            binding.similarMovieRecyclerView.adapter = similarImage
            binding.similarMovieCountInfoMovie.text = similarImage.itemCount.toString()

            binding.actorsCountInfoMovie.setOnClickListener {
                typeAllStaff = "Актёры"
                loadFragment(AllStaffFragment())
            }

            binding.directorsCountInfoMovie.setOnClickListener {
                typeAllStaff = "Режиссерский состав"
                loadFragment(AllStaffFragment())
            }
        }
    }

    private fun loadFragment(fragment: Fragment) {
        val transaction = fragmentManager?.beginTransaction()
        transaction?.setCustomAnimations(R.anim.activity_in, R.anim.activity_out)
        transaction?.replace(R.id.container, fragment)
        transaction?.commit()
    }

    private fun duration(duration: Int): String {
        var hours = 0
        var minutes = 0
        return if (duration > 60) {
            hours = duration / 60
            minutes = duration % 60
            "$hours ч $minutes мин"
        } else "$duration мин"
    }

    private fun onClickPhoto(item: SimilarItems, id: Int) {
        idFilm = id
        loadFragment(MovieInfoFragment())
    }

    private fun onClickStaff(item: ActorsList, id: Int) {
        idPerson = id
        loadFragment(StaffInfoFragment())
    }
}