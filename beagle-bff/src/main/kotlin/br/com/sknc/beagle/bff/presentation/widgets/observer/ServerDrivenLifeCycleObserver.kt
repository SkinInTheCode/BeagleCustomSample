package br.com.sknc.beagle.bff.presentation.widgets.observer

import br.com.zup.beagle.annotation.RegisterWidget
import br.com.zup.beagle.core.ServerDrivenComponent
import br.com.zup.beagle.widget.Widget
import br.com.zup.beagle.widget.action.Action
import br.com.zup.beagle.widget.context.ContextComponent
import br.com.zup.beagle.widget.context.ContextData

@RegisterWidget
data class ServerDrivenLifeCycleObserver(
    val child: ServerDrivenComponent? = null,
    override var context: ContextData? = null,
    val onViewShow: List<Action>? = null,
    val onViewHide: List<Action>? = null,
    val onInit: List<Action>? = null
) : Widget(), ContextComponent