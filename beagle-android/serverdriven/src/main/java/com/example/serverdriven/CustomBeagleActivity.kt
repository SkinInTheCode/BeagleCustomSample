package com.example.serverdriven

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.widget.Toolbar
import br.com.zup.beagle.android.annotation.BeagleComponent
import br.com.zup.beagle.android.view.BeagleActivity
import br.com.zup.beagle.android.view.ServerDrivenState

@BeagleComponent
class CustomBeagleActivity : BeagleActivity() {

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
    }

    override fun getServerDrivenContainerId(): Int {
        TODO("Not yet implemented")
    }

    override fun getToolbar(): Toolbar {
        TODO("Not yet implemented")
    }

    override fun onServerDrivenContainerStateChanged(state: ServerDrivenState) {

        when(state){

            is ServerDrivenState.Success -> {

            }
        }

    }

}