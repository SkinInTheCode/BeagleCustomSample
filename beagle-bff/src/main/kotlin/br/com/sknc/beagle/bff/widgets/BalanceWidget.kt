package br.com.sknc.beagle.bff.widgets

import br.com.zup.beagle.annotation.RegisterWidget
import br.com.zup.beagle.widget.Widget
import br.com.zup.beagle.widget.action.Action
import br.com.zup.beagle.widget.context.Bind
import br.com.zup.beagle.widget.context.ContextComponent
import br.com.zup.beagle.widget.context.ContextData

@RegisterWidget
class BalanceWidget(
        override var context: ContextData? = null,
        val viewCycleState: Bind<String>,
        val onInit: List<Action>? = null,
        val state: Bind<BalanceState>,
        val balance: Bind<Double>,
        val errorAction: List<Action>? = null
) : Widget(), ContextComponent

data class BalanceData(val state : BalanceState, val balance : Double)

enum class BalanceState {
    LOADING,
    ERROR,
    SUCCESS
}
