package com.example.skillcinema.splashscreen

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.skillcinema.*
import com.example.skillcinema.databinding.ActivitySplashScreenBinding
import com.example.skillcinema.entity.*
import com.example.skillcinema.recyclerview.*
import com.example.skillcinema.recyclerview.detectives.DetectivesPagedAdapter
import com.example.skillcinema.recyclerview.dram.DramPagedAdapter
import com.example.skillcinema.recyclerview.popular.PopularPagedAdapter
import com.example.skillcinema.recyclerview.premier.PremierAdapter
import com.example.skillcinema.recyclerview.premier.PremierPagedAdapter
import com.example.skillcinema.recyclerview.ser.SerPagedAdapter
import com.example.skillcinema.recyclerview.top.TopPagedAdapter
import com.example.skillcinema.screen.MainActivity
import com.example.skillcinema.screen.movieinfo.MovieInfoFragment
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashScreenBinding
    lateinit var handler: Handler
    private val viewModel: MoviePagedListViewModel by viewModels()


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        this.overridePendingTransition(R.anim.activity_in, R.anim.activity_out)

        handler = Handler()

        //Экран с подборкой фильмов
        viewModel.filmsSer.onEach {
            serAdapter.setData(it)
        }.launchIn(lifecycleScope)

        viewModel.filmsDram.onEach {
            dramAdapter.setData(it)
        }.launchIn(lifecycleScope)

        viewModel.filmsDet.onEach {
            detectivesAdapter.setData(it)
        }.launchIn(lifecycleScope)

        viewModel.filmsTop.onEach {
            topAdapter.setData(it)
        }.launchIn(lifecycleScope)

        viewModel.filmsPopular.onEach {
            popularAdapter.setData(it)
        }.launchIn(lifecycleScope)

        viewModel.filmsPremier.onEach {
            premierAdapter.setData(it)
        }.launchIn(lifecycleScope)

        handler.postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 1000)
    }
}