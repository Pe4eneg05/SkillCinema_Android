package com.example.skillcinema.recyclerview

import com.example.skillcinema.recyclerview.detectives.DetectivesAdapter
import com.example.skillcinema.recyclerview.detectives.DetectivesPagedAdapter
import com.example.skillcinema.recyclerview.dram.DramAdapter
import com.example.skillcinema.recyclerview.dram.DramPagedAdapter
import com.example.skillcinema.recyclerview.popular.PopularAdapter
import com.example.skillcinema.recyclerview.popular.PopularPagedAdapter
import com.example.skillcinema.recyclerview.premier.PremierAdapter
import com.example.skillcinema.recyclerview.ser.SerAdapter
import com.example.skillcinema.recyclerview.ser.SerPagedAdapter
import com.example.skillcinema.recyclerview.top.TopPagedAdapter

var keyword = ""
var typeAllFilms = ""
var typeAllStaff = ""
var idFilm = 0
var idPerson = 0

var typeScreen = false

var countDirectors = 0
var countActors = 0