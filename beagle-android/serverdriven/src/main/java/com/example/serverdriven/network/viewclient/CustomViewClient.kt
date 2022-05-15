package com.example.serverdriven.network.viewclient

import br.com.zup.beagle.android.annotation.BeagleComponent
import br.com.zup.beagle.android.networking.*

//@BeagleComponent
class CustomViewClient(
    private val httpClient: HttpClient,
    private val cachedResponses: MutableMap<String, ResponseData> = mutableMapOf()
) : ViewClient {

    override fun fetch(requestData: RequestData, onSuccess: OnSuccess, onError: OnError): RequestCall? {
        val cachedResponse = cachedResponses[requestData.url]
        return if (cachedResponse != null) {
            onSuccess(cachedResponse)
            null
        } else {

            httpClient.execute(requestData,
                onSuccess = { response ->
                    onSuccess(response)
                    cachedResponses[requestData.url] = response
                },
                onError = { error ->
                    onError(error)
                }
            )
        }
    }

    override fun prefetch(requestData: RequestData, onSuccess: OnSuccess, onError: OnError): RequestCall? {
        val cachedResponse = cachedResponses[requestData.url]
        return if (cachedResponse != null) {
            onSuccess(cachedResponse)
            null
        } else {
            httpClient.execute(requestData,onSuccess, onError)
        }
    }
}