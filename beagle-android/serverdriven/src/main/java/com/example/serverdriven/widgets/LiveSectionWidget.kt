package com.example.serverdriven.widgets

import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import br.com.zup.beagle.android.action.Action
import br.com.zup.beagle.android.context.Bind
import br.com.zup.beagle.android.context.ContextComponent
import br.com.zup.beagle.android.context.ContextData
import br.com.zup.beagle.android.utils.Observer
import br.com.zup.beagle.android.utils.observeBindChanges
import br.com.zup.beagle.android.utils.toView
import br.com.zup.beagle.android.widget.RootView
import br.com.zup.beagle.android.widget.WidgetView
import br.com.zup.beagle.annotation.RegisterWidget
import br.com.zup.beagle.core.ServerDrivenComponent

@RegisterWidget
class LiveSectionWidget(
    override var context: ContextData,
    private val state: Bind<LiveSectionState>,
    private val onInit: List<Action>,
    private val successWidget: Bind<ServerDrivenComponent?>,
    private val loadStateWidget: ServerDrivenComponent? = null,
    private val errorStateWidget: ServerDrivenComponent? = null,
    private val onRetry: List<Action>? = null
) : WidgetView(), ContextComponent {

    override fun buildView(rootView: RootView) = FrameLayout(rootView.getContext()).also { view ->
        observeBindChanges(rootView, view, state) { liveSectionState ->
            when (liveSectionState) {

                LiveSectionState.LOADING -> loadStateWidget?.let { view.buildWidget(rootView, it) }

                LiveSectionState.ERROR -> errorStateWidget?.let { view.buildWidget(rootView, it) }

                LiveSectionState.RETRY -> onRetry?.forEach { action ->
                    action.execute(rootView, view)
                }
            }
        }
    }.also { view ->
        observeBindChanges(
            rootView = rootView,
            view,
            successWidget,
            observes = view.observeWidget(rootView)
        )
    }.also { view ->
        onInit.forEach {
            it.execute(rootView, view)
        }
    }

    private fun FrameLayout.observeWidget(rootView: RootView): Observer<ServerDrivenComponent?> =
        { widget ->
            buildWidget(rootView, widget)
        }

    private fun FrameLayout.buildWidget(rootView: RootView, widget: ServerDrivenComponent?) {
        widget?.let {
            removeAllViews()
            addView(it.toView(rootView.getContext() as AppCompatActivity))
        }
    }

    enum class LiveSectionState {
        LOADING,
        ERROR,
        RETRY
    }
}