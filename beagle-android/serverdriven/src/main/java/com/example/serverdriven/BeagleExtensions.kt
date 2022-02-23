package com.example.serverdriven

import br.com.zup.beagle.android.context.Bind
import br.com.zup.beagle.android.context.GlobalContext

fun onViewStateChange(screen: String, viewState: ViewCycleListener.ViewState) {
    GlobalContext.set(viewState.state, screen.plus(".onViewStateChange"))
}

interface ViewCycleListener {
    var viewCycleState: Bind<String>

    enum class ViewState(val state : String) {
        RESUME("onResume"),
        PAUSE("onPause")
    }
}


