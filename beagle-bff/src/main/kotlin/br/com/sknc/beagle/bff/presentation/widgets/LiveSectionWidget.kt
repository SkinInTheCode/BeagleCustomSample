package br.com.sknc.beagle.bff.presentation.widgets

import br.com.zup.beagle.annotation.RegisterWidget
import br.com.zup.beagle.core.ServerDrivenComponent
import br.com.zup.beagle.widget.Widget
import br.com.zup.beagle.widget.action.Action
import br.com.zup.beagle.widget.context.Bind
import br.com.zup.beagle.widget.context.ContextComponent
import br.com.zup.beagle.widget.context.ContextData

@RegisterWidget
data class LiveSectionWidget(
    override var context: ContextData,
    val state: Bind<LiveSectionState>,
    val onInit: List<Action>,
    val successWidget: Bind<ServerDrivenComponent?>,
    val loadStateWidget: ServerDrivenComponent? = null,
    val errorStateWidget: ServerDrivenComponent? = null,
    val onRetry: List<Action>? = null
) : Widget(), ContextComponent {

    enum class LiveSectionState {
        LOADING,
        ERROR,
        RETRY
    }

    data class LiveSectionDataContext(
        val state: LiveSectionState,
        val successWidget: ServerDrivenComponent? = null
    )

}