package com.example.serverdriven.network

import br.com.zup.beagle.android.networking.*
import com.squareup.okhttp.*
import java.io.IOException
import java.net.HttpRetryException

class CustomHttpClient(
    private val okHttpClient: OkHttpClient = OkHttpClient()
) : HttpClient {

    override fun execute(
            request: RequestData,
            onSuccess: (responseData: ResponseData) -> Unit,
            onError: (responseData: ResponseData) -> Unit): RequestCall {
        return makeRequest(request, onSuccess, onError)
    }

    private fun makeRequest(request: RequestData,
                            onSuccess: (responseData: ResponseData) -> Unit,
                            onError: (responseData: ResponseData) -> Unit) : RequestCall {

        val call = request.createCall(client = okHttpClient)

        call.enqueue(object : Callback {
            override fun onResponse(response: Response?) {
                 response?.let {
                    with(response.toRespondData()){
                        if (response.code() in 200..299)
                            onSuccess.invoke(this)
                        else
                            onFailure(response.request(), HttpRetryException(response.message(), response.code()))
                    }
                }
            }

            override fun onFailure(request: Request?, e: IOException?) {
                onError.invoke(
                    ResponseData(
                        statusCode = 0,
                        data = byteArrayOf(),
                        mapOf()
                    )
                )
            }
        })

        return object : RequestCall{
            override fun cancel() {
                call.cancel()
            }
        }

    }

    private fun Response.toRespondData () = ResponseData(
            statusCode = code(),
            data = body()?.bytes() ?: byteArrayOf()
    )

    private fun RequestData.createCall(client : OkHttpClient) = client.newCall(
        with(httpAdditionalData){
            Request.Builder()
                .url(url)
                .headers(Headers.of(headers))
                .method(
                    method.name,
                    body.toString().toRequestBody(method)
                ).build()
        }

    )

    private fun String?.toRequestBody(httpMethod : HttpMethod) = when(httpMethod) {
        HttpMethod.GET -> null
        else ->  RequestBody.create(MediaType.parse("application/json; charset=UTF-8"), this ?: "")
    }

}