package com.example.skillcinema.recyclerview

import com.example.skillcinema.api.retrofit
import com.example.skillcinema.entity.*

class MoviePagedListRepository {


    suspend fun getFilmsByKeywordList(page: Int, keyword: String): List<FilmsByKeyword> {
        return retrofit.filmsByKeywordList(page, keyword).films
    }


    suspend fun getFilmsAllList(page: Int): List<FilmsAll> {
        return retrofit.allFilmsList(page).items
    }


    suspend fun getStaffInfo(url: String): StaffInfo {
        return retrofit.staffInfo(url)
    }


    suspend fun getSimilarList(url: String): List<SimilarItems> {
        return retrofit.similarList(url).items
    }


    suspend fun getPhotoList(url: String): List<PhotoItems> {
        return retrofit.imageList(url).items
    }


    suspend fun getActorsList(filmId: Int): List<ActorsList>{
        return retrofit.actorsList(filmId)
    }


    suspend fun getMovieInfo(url: String): MovieInfo {
        return retrofit.movieInfo(url)
    }


    suspend fun getPremierListHome(year: Int, month: String): List<FilmsPremier> {
        return retrofit.premierListHome(year, month).items.shuffled()
    }


    suspend fun getPopularList(page: Int): List<FilmsTopPopular> {
        return retrofit.popularList(page).films
    }
    suspend fun getPopularListHome(): List<FilmsTopPopular> {
        return retrofit.popularListHome().films.shuffled()
    }


    suspend fun getDetectivesList(page: Int): List<FilmsSerDramDet> {
        return retrofit.detectivesList(page).items
    }
    suspend fun getDetectivesListHome(): List<FilmsSerDramDet> {
        return retrofit.detectivesListHome().items.shuffled()
    }


    suspend fun getTopList(page: Int): List<FilmsTopPopular> {
        return retrofit.topList(page).films
    }
    suspend fun getTopListHome(): List<FilmsTopPopular> {
        return retrofit.topListHome().films.shuffled()
    }


    suspend fun getDramList(page: Int): List<FilmsSerDramDet> {
        return retrofit.dramList(page).items
    }
    suspend fun getDramListHome(): List<FilmsSerDramDet> {
        return retrofit.dramListHome().items.shuffled()
    }


    suspend fun getSerList(page: Int): List<FilmsSerDramDet> {
        return retrofit.serList(page).items
    }
    suspend fun getSerListHome(): List<FilmsSerDramDet> {
        return retrofit.serListHome().items.shuffled()
    }
}