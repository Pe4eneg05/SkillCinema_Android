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
import androidx.recyclerview.widget.GridLayoutManager
import com.example.skillcinema.R
import com.example.skillcinema.databinding.FragmentAllStaffBinding
import com.example.skillcinema.databinding.FragmentFilmographyBinding
import com.example.skillcinema.entity.*
import com.example.skillcinema.recyclerview.*
import com.example.skillcinema.recyclerview.actor.ActorsAdapter
import com.example.skillcinema.recyclerview.directors.DirectorsAdapter
import com.example.skillcinema.recyclerview.staffinfo.filmography.FilmographyAdapter
import com.example.skillcinema.screen.movieinfo.MovieInfoFragment
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class AllStaffFragment : Fragment() {

    private var _binding: FragmentAllStaffBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MoviePagedListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAllStaffBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.titleAllStaff.text = typeAllStaff

        binding.buttonBackAllStaff.setOnClickListener {
            loadFragment(StaffInfoFragment())
        }

        binding.recyclerViewAllStaff.layoutManager = GridLayoutManager(context, 2)

        when (typeAllStaff) {
            "Актёры" -> {

                val actorAdapter = ActorsAdapter({ onClickStaff(it, it.staffId) }, true)

                viewModel.actors.onEach {
                    actorAdapter.setData(it)
                }.launchIn(lifecycleScope)

                binding.recyclerViewAllStaff.adapter = actorAdapter
            }
            "Режиссерский состав" -> {

                val directorsAdapter = DirectorsAdapter({ onClickStaff(it, it.staffId) }, true)

                viewModel.actors.onEach {
                    directorsAdapter.setData(it)
                }.launchIn(lifecycleScope)

                binding.recyclerViewAllStaff.adapter = directorsAdapter
            }
        }
    }

    private fun loadFragment(fragment: Fragment) {
        val transaction = fragmentManager?.beginTransaction()
        transaction?.setCustomAnimations(R.anim.activity_in, R.anim.activity_out)
        transaction?.replace(R.id.container, fragment)
        transaction?.commit()
    }

    private fun onClickStaff(item: ActorsList, id: Int) {
        idPerson = id
        loadFragment(StaffInfoFragment())
    }
}