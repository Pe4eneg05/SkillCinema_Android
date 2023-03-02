package com.example.skillcinema.recyclerview.allfilms

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.skillcinema.entity.FilmsAll
import com.example.skillcinema.entity.FilmsSerDramDet
import com.example.skillcinema.recyclerview.MoviePagedListRepository

class AllFilmsPagedSource : PagingSource<Int, FilmsAll>(){

    private val repository = MoviePagedListRepository()

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, FilmsAll> {
        val page = params.key ?: FIRST_PAGE
        return kotlin.runCatching {
            repository.getFilmsAllList(page)
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

    override fun getRefreshKey(state: PagingState<Int, FilmsAll>): Int? = FIRST_PAGE
}