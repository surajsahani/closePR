package com.example.closepr.paging


import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.closepr.api.PullApiService
import com.example.closepr.models.Pull
import com.example.closepr.utils.LinkParser
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class PullPagingSource @Inject constructor(
    private val pullApiService: PullApiService
) : PagingSource<String, Pull>() {

    companion object {
        const val USER = "mozilla-mobile"
        const val REPO = "fenix"
    }

    override suspend fun load(params: LoadParams<String>): LoadResult<String, Pull> {

        return try {
            val pageUrl = params.key
            val response =
                if (pageUrl != null) pullApiService.getPullsFromLink(pageUrl) else pullApiService.getAllPulls(
                    USER, REPO
                )
            val pulls = response.body() ?: listOf()
            val linkHeader = response.headers()["link"]
            LoadResult.Page(
                data = pulls,
                prevKey = LinkParser.parsePrev(linkHeader),
                nextKey = LinkParser.parseNext(linkHeader)
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<String, Pull>): String? {
        return state.anchorPosition?.let { state.closestItemToPosition(it)?.id?.toString() }
    }
}