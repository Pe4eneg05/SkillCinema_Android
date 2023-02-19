package com.example.skillcinema.recyclerview.premier

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.skillcinema.entity.FilmsPremier
import com.example.skillcinema.recyclerview.MoviePagedListRepository
import java.text.SimpleDateFormat
import java.util.*

class PremierPagedSource : PagingSource<Int, FilmsPremier>() {

    private val repository = MoviePagedListRepository()

    override fun getRefreshKey(state: PagingState<Int, FilmsPremier>): Int? = FIRST_PAGE

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, FilmsPremier> {
        val page = params.key ?: 1
        return kotlin.runCatching {
            repository.getPremierListHome(year, month)
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

        @RequiresApi(Build.VERSION_CODES.O)
        val month = Calendar.getInstance()
            .getDisplayName(Calendar.MONTH, Calendar.LONG_FORMAT, Locale.ENGLISH)!!.toString()
            .uppercase()

        @SuppressLint("SimpleDateFormat")
        val year = SimpleDateFormat("yyyy").format(Calendar.getInstance().time).toInt()
    }
}