package com.vesync.wanandroid.network

import retrofit2.http.*

/**
 * Author: Nickhou
 * Date: 2021/12/4 15:57
 * Description: 网络请求接口 接口地址----》https://www.wanandroid.com/blog/show/2
 * History:
 * <author> <time> <version> <desc>
 * Nickhou 2021/12/4 1.0 首次创建
 */
interface HttpService {

    companion object {
        const val url = "https://www.wanandroid.com"
    }

    //首页
    @GET("/article/list/{page}/json")
    suspend fun getIndexList(@Path("page") page: Int): BasicBean<ListWrapper<Article>>
}
