package com.example.serverdriven.widgets.observer

import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import br.com.zup.beagle.android.widget.RootView

internal interface ServerDrivenLifeCycleObserverDelegate {

    fun initializeDelegate(
        rootView: RootView,
        originView: View,
        listener: LifeCycleEventListener
    )
}

internal interface LifeCycleEventListener {

    fun onViewShow(rootView: RootView, originView: View)
    fun onViewHide(rootView: RootView, originView: View)

    fun onViewCreate()
    fun onViewDestroy()


}

internal class ServerDrivenLifeCycleObserverDelegateImpl : ServerDrivenLifeCycleObserverDelegate,
    LifecycleEventObserver {

    private lateinit var listener: LifeCycleEventListener
    private lateinit var lifecycle: Lifecycle
    private lateinit var rootView: RootView
    private lateinit var originView: View

    override fun initializeDelegate(
        rootView: RootView,
        originView: View,
        listener: LifeCycleEventListener
    ) {
        this.listener = listener
        this.rootView = rootView
        this.lifecycle = rootView.getLifecycleOwner().lifecycle
        this.originView = originView
        this.lifecycle.addObserver(this)
    }

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        when (event) {

            Lifecycle.Event.ON_CREATE ->  listener.onViewCreate()

            Lifecycle.Event.ON_RESUME -> listener.onViewShow(rootView, originView)

            Lifecycle.Event.ON_PAUSE -> listener.onViewHide(rootView, originView)

            Lifecycle.Event.ON_DESTROY -> {
                listener.onViewDestroy()
                this.lifecycle.removeObserver(this)
            }

            else -> {}
        }
    }

}
