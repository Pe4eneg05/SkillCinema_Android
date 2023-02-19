package com.example.skillcinema.recyclerview.top

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.skillcinema.entity.FilmsTopPopular
import com.example.skillcinema.recyclerview.MoviePagedListRepository

class TopPagedSource : PagingSource<Int, FilmsTopPopular>(){

    private val repository = MoviePagedListRepository()

    override fun getRefreshKey(state: PagingState<Int, FilmsTopPopular>): Int = FIRST_PAGE

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, FilmsTopPopular> {
        val page = params.key ?: 1
        return kotlin.runCatching {
            repository.getTopList(page)
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