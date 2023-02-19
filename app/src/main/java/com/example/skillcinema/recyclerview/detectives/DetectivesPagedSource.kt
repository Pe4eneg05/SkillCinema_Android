package com.example.skillcinema.recyclerview.detectives

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.skillcinema.entity.FilmsSerDramDet
import com.example.skillcinema.recyclerview.MoviePagedListRepository

class DetectivesPagedSource : PagingSource<Int, FilmsSerDramDet>(){

    private val repository = MoviePagedListRepository()

    override fun getRefreshKey(state: PagingState<Int, FilmsSerDramDet>): Int = FIRST_PAGE

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, FilmsSerDramDet> {
        val page = params.key ?: FIRST_PAGE
        return kotlin.runCatching {
            repository.getDetectivesList(page)
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