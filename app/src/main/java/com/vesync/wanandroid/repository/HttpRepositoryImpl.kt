package com.vesync.wanandroid.repository

import com.vesync.wanandroid.network.HttpService

class HttpRepositoryImpl constructor(private val apiService: HttpService): BaseRepository(), HttpRepository {

    /** 首页列表 */
    override fun getIndexData() = pager { page->  apiService.getIndexList(page) }

}