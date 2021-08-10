package com.dindinn.app.data.source.remote.interceptor

import android.content.Context
import com.dindinn.app.util.Utils.readFileFromAssets
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody

class MockRequestInterceptor(private val context: Context) : Interceptor {

    companion object {
        private val JSON_MEDIA_TYPE = "application/json".toMediaTypeOrNull()
        private const val MOCK = "mocks"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        val header = request.header(MOCK)

        if (header != null) {
            val filename = request.url.pathSegments.last()
            return Response.Builder()
                .request(request)
                .protocol(Protocol.HTTP_1_1)
                .message("")
                .code(200)
                .body(
                    ResponseBody.create(
                        JSON_MEDIA_TYPE,
                        context.readFileFromAssets("mocks/$filename.json")
                    )
                )
                .build()
        }

        return chain.proceed(request.newBuilder().removeHeader(MOCK).build())
    }
}