package com.example.serverdriven

import android.app.Application
import br.com.zup.beagle.scaffold.BeagleScaffold
import com.example.designsystem.component.sharedpreference.Storage
import com.example.serverdriven.network.AppHttpClientFactory

class ServerDrivenInitializer {

    companion object {

        fun initialize(application: Application,
                       storage: Storage) {

            with(BeagleScaffold(BeagleSetup())){
                httpClientFactory.let { factory ->
                    if (factory is AppHttpClientFactory)
                        factory.storage = storage
                }

                init(application)
            }
        }

    }


}