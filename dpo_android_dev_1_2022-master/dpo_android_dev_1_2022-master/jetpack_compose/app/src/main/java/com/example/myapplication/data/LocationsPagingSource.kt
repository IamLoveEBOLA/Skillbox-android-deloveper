package com.example.myapplication.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.myapplication.data.data_classes.locations.ResultsLocation
import kotlinx.coroutines.delay
import javax.inject.Inject


class LocationsPagingSource @Inject constructor(
    private val locationsRepository: LocationsRepository
): PagingSource<Int , ResultsLocation>() {
    override fun getRefreshKey(state: PagingState<Int , ResultsLocation>): Int? = FIRST_PAGE

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int , ResultsLocation> {
        val page = params.key?: FIRST_PAGE
        return kotlin.runCatching {
            delay(3000)
            locationsRepository.getLocations(page)
        }.fold(
            onSuccess = {
                it?.let{ it1 ->
                    LoadResult.Page(
                        data = it1.results,
                        prevKey = null,
                        nextKey = if (it.results.isEmpty()) null else page +1
                    )
                }
            },
            onFailure = {LoadResult.Error(it)}
        )!!
    }


    companion object{
        private val FIRST_PAGE = 1
    }
}