package com.example.skillcinema.recyclerview

import com.example.skillcinema.entity.MovieInfo
import com.example.skillcinema.recyclerview.detectives.DetectivesAdapter
import com.example.skillcinema.recyclerview.detectives.DetectivesPagedAdapter
import com.example.skillcinema.recyclerview.dram.DramAdapter
import com.example.skillcinema.recyclerview.dram.DramPagedAdapter
import com.example.skillcinema.recyclerview.popular.PopularAdapter
import com.example.skillcinema.recyclerview.popular.PopularPagedAdapter
import com.example.skillcinema.recyclerview.premier.PremierAdapter
import com.example.skillcinema.recyclerview.premier.PremierPagedAdapter
import com.example.skillcinema.recyclerview.ser.SerAdapter
import com.example.skillcinema.recyclerview.ser.SerPagedAdapter
import com.example.skillcinema.recyclerview.top.TopAdapter
import com.example.skillcinema.recyclerview.top.TopPagedAdapter

var typeAllFilms = ""
var idFilm = 0

lateinit var infoFilm: MovieInfo

lateinit var pagedAdapterPremier: PremierPagedAdapter
val premierAdapter = PremierAdapter()

lateinit var pagedAdapterPopular: PopularPagedAdapter
val popularAdapter = PopularAdapter()

lateinit var pagedAdapterDetectives: DetectivesPagedAdapter
val detectivesAdapter = DetectivesAdapter()

lateinit var pagedAdapterTop: TopPagedAdapter
val topAdapter = TopAdapter()

lateinit var pagedAdapterDram: DramPagedAdapter
val dramAdapter = DramAdapter()

lateinit var pagedAdapterSer: SerPagedAdapter
val serAdapter = SerAdapter()