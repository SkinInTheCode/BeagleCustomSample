package com.example.serverdriven.widgets

import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import br.com.zup.beagle.android.action.Action
import br.com.zup.beagle.android.context.Bind
import br.com.zup.beagle.android.context.ContextComponent
import br.com.zup.beagle.android.context.ContextData
import br.com.zup.beagle.android.utils.Observer
import br.com.zup.beagle.android.utils.observeBindChanges
import br.com.zup.beagle.android.widget.RootView
import br.com.zup.beagle.android.widget.WidgetView
import br.com.zup.beagle.android.annotation.RegisterWidget
import br.com.zup.beagle.android.widget.core.ServerDrivenComponent
import com.example.serverdriven.loadView

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

    override fun buildView(rootView: RootView) = FrameLayout(rootView.getContext()).apply {
        observeBindChanges(rootView, this, state) { liveSectionState ->
            when (liveSectionState) {

                LiveSectionState.LOADING -> loadStateWidget?.let { buildWidget(rootView, it) }

                LiveSectionState.ERROR -> errorStateWidget?.let { buildWidget(rootView, it) }

                LiveSectionState.RETRY -> onRetry?.forEach { action ->
                    action.execute(rootView, this)
                }
            }
        }

        observeBindChanges(
            rootView = rootView,
            this,
            successWidget,
            observes = observeWidget(rootView)
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
            loadView(rootView.getContext(), widget)
        }
    }

    enum class LiveSectionState {
        LOADING,
        ERROR,
        RETRY
    }
}
