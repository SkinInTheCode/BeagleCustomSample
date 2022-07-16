package br.com.sknc.beagle.bff.presentation.widgets

import br.com.zup.beagle.annotation.RegisterWidget
import br.com.zup.beagle.widget.Widget
import br.com.zup.beagle.widget.action.Action
import br.com.zup.beagle.widget.context.Bind
import br.com.zup.beagle.widget.context.ContextComponent
import br.com.zup.beagle.widget.context.ContextData

@RegisterWidget
class BalanceWidget(
    val onInit: List<Action>? = null,
    val state: Bind<BalanceState>,
    val balance: Bind<Double>,
    val errorAction: List<Action>? = null,
    override var context: ContextData? = null,
    val balanceToggle: BalanceToggle? = null
) : Widget(), ContextComponent

data class BalanceContextData(val state : BalanceState, val balance : Double = 0.0, val balanceVisible: Boolean = true)

enum class BalanceState {
    LOADING,
    ERROR,
    SUCCESS
}
data class BalanceToggle(
    val onHideAction: List<Action>? = null,
    val onShowAction: List<Action>? = null,
    val balanceToggleVisible: Bind<Boolean>? = null,
)