package com.example.serverdriven

import android.app.Application
import android.os.Environment
import br.com.zup.beagle.scaffold.BeagleScaffold
import com.example.designsystem.component.sharedpreference.Storage
import com.example.serverdriven.network.AppHttpClientFactory

data class ServerDrivenInitializer(
    val storage: Storage
) {

    companion object {

        lateinit var environment: ServerDrivenInitializer
            private set

        fun initialize(application: Application,
                       storage: Storage) {

            environment = ServerDrivenInitializer(storage)

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