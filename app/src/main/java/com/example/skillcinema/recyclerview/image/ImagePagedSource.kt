package com.example.skillcinema.recyclerview.image

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.skillcinema.entity.FilmsSerDramDet
import com.example.skillcinema.entity.PhotoItems
import com.example.skillcinema.recyclerview.MoviePagedListRepository
import com.example.skillcinema.recyclerview.idFilm

class ImagePagedSource : PagingSource<Int, PhotoItems>(){

    private val repository = MoviePagedListRepository()

    override fun getRefreshKey(state: PagingState<Int, PhotoItems>): Int = FIRST_PAGE

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PhotoItems> {
        val page = params.key ?: FIRST_PAGE
        val urlImage = "/api/v2.2/films/$idFilm/images?page=$page"
        return kotlin.runCatching {
            repository.getPhotoList(urlImage)
        }.fold(
            onSuccess = {
                LoadResult.Page(
                    data = it,
                    prevKey = null,
                    nextKey = if (it.isEmpty()) null else page + 1
                )
            },
            onFailure = { LoadResult.Error(it) }
        )
    }

    private companion object {
        private val FIRST_PAGE = 1
    }
}