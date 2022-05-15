package com.example.serverdriven.widgets

import br.com.zup.beagle.android.action.Action
import br.com.zup.beagle.android.annotation.RegisterWidget
import br.com.zup.beagle.android.context.*
import br.com.zup.beagle.android.utils.observeBindChanges
import br.com.zup.beagle.android.widget.RootView
import br.com.zup.beagle.android.widget.WidgetView
import com.example.designsystem.uikit.BalanceView

@RegisterWidget
class BalanceWidget(
    private val onInit: List<Action>? = null,
    private val state: Bind<BalanceState>,
    private val balance: Bind<Double>,
    private val errorAction: List<Action>? = null,
    override var context: ContextData? = null
) : WidgetView(), ContextComponent {

    override fun buildView(rootView: RootView) = BalanceView(rootView.getContext()).apply {

        rootView.observeBalance(this)
        rootView.observeState(this)
    }.also { view ->
        onInit?.forEach { it.execute(rootView, view) }
    }

    private fun RootView.observeBalance(view: BalanceView) {
        observeBindChanges(
            rootView = this,
            view,
            balance
        ) { value ->
            value?.let {
                view.setBalance(it)
            }
        }
    }

    private fun RootView.observeState(view: BalanceView) {
        observeBindChanges(this, view, state)
        { balanceState ->
            when (balanceState) {

                BalanceState.LOADING -> view.showLoading(true)

                BalanceState.ERROR -> view.showError {
                    errorAction?.forEach {
                        it.execute(this, view)
                    }
                }

                BalanceState.SUCCESS -> view.showBalance()
            }
        }
    }
}


enum class BalanceState {
    LOADING,
    ERROR,
    SUCCESS
}




