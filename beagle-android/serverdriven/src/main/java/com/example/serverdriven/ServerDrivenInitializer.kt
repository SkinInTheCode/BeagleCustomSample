package com.example.serverdriven

import android.app.Application
import br.com.zup.beagle.scaffold.BeagleScaffold


class ServerDrivenInitializer {

    companion object {

        fun initialize(application: Application) {
            BeagleScaffold(BeagleSetup()).init(application)
        }

    }


}