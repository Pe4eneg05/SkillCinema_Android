package com.example.skillcinema.recyclerview.dram

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.skillcinema.entity.FilmsSerDramDet
import com.example.skillcinema.recyclerview.MoviePagedListRepository

class DramPagedSource : PagingSource<Int, FilmsSerDramDet>(){

    private val repository = MoviePagedListRepository()

    override fun getRefreshKey(state: PagingState<Int, FilmsSerDramDet>): Int? = FIRST_PAGE

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, FilmsSerDramDet> {
        val page = params.key ?: 1
        return kotlin.runCatching {
            repository.getDramList(page)
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