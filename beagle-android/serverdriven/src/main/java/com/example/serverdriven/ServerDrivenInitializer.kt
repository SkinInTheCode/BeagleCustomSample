package com.example.serverdriven

import android.app.Application
import br.com.zup.beagle.android.setup.BeagleSdk
import br.com.zup.beagle.scaffold.BeagleScaffold
import com.example.designsystem.component.sharedpreference.Storage
import com.example.serverdriven.network.AppHttpClientFactory
import com.example.serverdriven.network.CustomHttpClient


class ServerDrivenInitializer {

    companion object {

        fun initialize(application: Application,
                       storage: Storage) {
            with(BeagleScaffold(BeagleSetup())){

                httpClientFactory?.let { factory ->
                    if (factory is AppHttpClientFactory)
                        factory.storage = storage
                }

                init(application)
            }
        }

    }


}