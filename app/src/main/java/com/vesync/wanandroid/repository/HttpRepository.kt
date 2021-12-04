package com.vesync.wanandroid.repository

import androidx.paging.PagingData
import com.vesync.wanandroid.network.Article
import kotlinx.coroutines.flow.Flow

interface HttpRepository {
    //分页请求
    fun getIndexData(): Flow<PagingData<Article>>

}