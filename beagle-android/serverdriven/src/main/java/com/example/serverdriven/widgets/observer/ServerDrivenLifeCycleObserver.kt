package com.example.serverdriven.widgets.observer

import android.util.Log
import android.view.View
import android.widget.FrameLayout
import br.com.zup.beagle.android.action.Action
import br.com.zup.beagle.android.annotation.RegisterWidget
import br.com.zup.beagle.android.context.ContextComponent
import br.com.zup.beagle.android.context.ContextData
import br.com.zup.beagle.android.widget.RootView
import br.com.zup.beagle.android.widget.WidgetView
import br.com.zup.beagle.android.widget.core.ServerDrivenComponent
import com.example.serverdriven.loadView

@RegisterWidget
class ServerDrivenLifeCycleObserver(
    private val child: ServerDrivenComponent? = null,
    override var context: ContextData? = null,
    private val onViewShow: List<Action>? = null,
    private val onViewHide: List<Action>? = null,
    private val onInit: List<Action>? = null
) : WidgetView(), ContextComponent, LifeCycleEventListener,
    ServerDrivenLifeCycleObserverDelegate by ServerDrivenLifeCycleObserverDelegateImpl() {

    override fun buildView(rootView: RootView) = FrameLayout(rootView.getContext()).apply {

        rootView.observeLifeCycleOwner(this)
        child?.let { loadView(rootView.getContext(), it) }
    }.also { view ->
        onInit?.forEach { it.execute(rootView, view) }
    }

    private fun RootView.observeLifeCycleOwner(originView: View) {
        initializeDelegate(this, originView, this@ServerDrivenLifeCycleObserver)
    }

    override fun onViewShow(rootView: RootView, originView: View) {
        onViewShow?.forEach {
            it.execute(rootView, originView)
        }

        Log.i("ServerDrivenObserver", "ON_VIEW_SHOW")
    }

    override fun onViewHide(rootView: RootView, originView: View) {
        onViewHide?.forEach {
            it.execute(rootView, originView)
        }

        Log.i("ServerDrivenObserver", "ON_VIEW_HIDE")
    }

    override fun onViewCreate() {
        Log.i("ServerDrivenObserver", "ON_VIEW_CREATE")
    }

    override fun onViewDestroy() {
        Log.i("ServerDrivenObserver", "ON_VIEW_DESTROY")
    }
}