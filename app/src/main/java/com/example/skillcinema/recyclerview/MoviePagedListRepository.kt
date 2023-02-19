package com.example.skillcinema.recyclerview

import com.example.skillcinema.api.retrofit
import com.example.skillcinema.entity.FilmsPremier
import com.example.skillcinema.entity.FilmsSerDramDet
import com.example.skillcinema.entity.FilmsTopPopular
import com.example.skillcinema.entity.MovieInfo

class MoviePagedListRepository {

    suspend fun getMovieInfo(url: String): MovieInfo {
        return retrofit.movieInfo(url)
    }


    suspend fun getPremierListHome(year: Int, month: String): List<FilmsPremier> {
        return retrofit.premierListHome(year, month).items //.shuffled()
    }


    suspend fun getPopularList(page: Int): List<FilmsTopPopular> {
        return retrofit.popularList(page).films //.shuffled()
    }
    suspend fun getPopularListHome(): List<FilmsTopPopular> {
        return retrofit.popularListHome().films //.shuffled()
    }


    suspend fun getDetectivesList(page: Int): List<FilmsSerDramDet> {
        return retrofit.detectivesList(page).items //.shuffled()
    }
    suspend fun getDetectivesListHome(): List<FilmsSerDramDet> {
        return retrofit.detectivesListHome().items //.shuffled()
    }


    suspend fun getTopList(page: Int): List<FilmsTopPopular> {
        return retrofit.topList(page).films //.shuffled()
    }
    suspend fun getTopListHome(): List<FilmsTopPopular> {
        return retrofit.topListHome().films //.shuffled()
    }


    suspend fun getDramList(page: Int): List<FilmsSerDramDet> {
        return retrofit.dramList(page).items //.shuffled()
    }
    suspend fun getDramListHome(): List<FilmsSerDramDet> {
        return retrofit.dramListHome().items //.shuffled()
    }


    suspend fun getSerList(page: Int): List<FilmsSerDramDet> {
        return retrofit.serList(page).items //.shuffled()
    }
    suspend fun getSerListHome(): List<FilmsSerDramDet> {
        return retrofit.serListHome().items //.shuffled()
    }
}