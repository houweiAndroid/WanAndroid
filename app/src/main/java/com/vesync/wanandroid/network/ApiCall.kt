package com.vesync.wanandroid.network

import android.annotation.SuppressLint
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.SecureRandom
import java.security.cert.X509Certificate
import java.util.concurrent.TimeUnit
import javax.net.ssl.*

/**
 * Author: Nickhou
 * Date: 2021/12/4 15:57
 * Description: 网络生成工具
 * History:
 * <author> <time> <version> <desc>
 * Nickhou 2021/12/4 1.0 首次创建
 */
object ApiCall {

    /**
     * 请求超时时间
     */
    private const val DEFAULT_TIMEOUT = 30000
    private lateinit var SERVICE: HttpService

    //手动创建一个OkHttpClient并设置超时时间
    val retrofit: HttpService
        get() {
            if (!ApiCall::SERVICE.isInitialized) {
                SERVICE = Retrofit.Builder()
                    .client(okHttp)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(CoroutineCallAdapterFactory.invoke())
                    .baseUrl(HttpService.url)
                    .build()
                    .create(HttpService::class.java)
            }
            return SERVICE
        }

    //手动创建一个OkHttpClient并设置超时时间
    val okHttp: OkHttpClient
        get() {
            return OkHttpClient.Builder().run {
                connectTimeout(DEFAULT_TIMEOUT.toLong(), TimeUnit.MILLISECONDS)
                readTimeout(DEFAULT_TIMEOUT.toLong(), TimeUnit.MILLISECONDS)
                writeTimeout(DEFAULT_TIMEOUT.toLong(), TimeUnit.MILLISECONDS)
                //不验证证书
                sslSocketFactory(createSSLSocketFactory())
                hostnameVerifier(TrustAllNameVerifier())
                build()
            }
        }

    private fun createSSLSocketFactory(): SSLSocketFactory {
        lateinit var ssfFactory: SSLSocketFactory
        try {
            val sslFactory = SSLContext.getInstance("TLS")
            sslFactory.init(null, arrayOf(TrustAllCerts()), SecureRandom());
            ssfFactory = sslFactory.socketFactory
        } catch (e: Exception) {
            print("SSL错误：${e.message}")
        }
        return ssfFactory
    }

}

class TrustAllNameVerifier : HostnameVerifier {
    @SuppressLint("BadHostnameVerifier")
    override fun verify(hostname: String?, session: SSLSession?): Boolean = true
}

@SuppressLint("CustomX509TrustManager")
class TrustAllCerts : X509TrustManager {

    @SuppressLint("TrustAllX509TrustManager")
    override fun checkClientTrusted(chain: Array<out X509Certificate>?, authType: String?) {
    }

    @SuppressLint("TrustAllX509TrustManager")
    override fun checkServerTrusted(chain: Array<out X509Certificate>?, authType: String?) {
    }

    override fun getAcceptedIssuers(): Array<X509Certificate> = arrayOf()
}