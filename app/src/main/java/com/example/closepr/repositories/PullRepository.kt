package com.example.closepr.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.closepr.models.Pull
import com.example.closepr.paging.PullPagingSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PullRepository @Inject constructor() {

    @Inject
    lateinit var pullPagingSource: PullPagingSource

    fun getPulls(): Flow<PagingData<Pull>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { pullPagingSource }
        ).flow
    }

    companion object {
        const val NETWORK_PAGE_SIZE = 30
    }
}