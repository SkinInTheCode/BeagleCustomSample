package com.example.serverdriven.network

import br.com.zup.beagle.android.annotation.BeagleComponent
import br.com.zup.beagle.android.networking.HttpClient
import br.com.zup.beagle.android.networking.HttpClientFactory
import com.example.designsystem.component.sharedpreference.Storage

@BeagleComponent
class AppHttpClientFactory: HttpClientFactory {

    lateinit var storage: Storage

    override fun create(): HttpClient {
        return CustomHttpClient(storage)
    }
}