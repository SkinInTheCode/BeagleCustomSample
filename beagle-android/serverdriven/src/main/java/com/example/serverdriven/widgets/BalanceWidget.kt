package com.example.serverdriven.widgets

import br.com.zup.beagle.android.action.Action
import br.com.zup.beagle.android.action.SetContext
import br.com.zup.beagle.android.annotation.RegisterWidget
import br.com.zup.beagle.android.components.OnInitiableComponent
import br.com.zup.beagle.android.components.OnInitiableComponentImpl
import br.com.zup.beagle.android.context.*
import br.com.zup.beagle.android.utils.observeBindChanges
import br.com.zup.beagle.android.widget.RootView
import br.com.zup.beagle.android.widget.WidgetView
import br.com.zup.beagle.android.widget.core.ServerDrivenComponent
import com.example.designsystem.uikit.BalanceToggleListener
import com.example.designsystem.uikit.BalanceView
import com.example.serverdriven.actions.SaveValueLocalAction

@RegisterWidget
class BalanceWidget(
    override val onInit: List<Action>? = null,
    private val state: Bind<BalanceState>,
    private val balance: Bind<Double>,
    private val errorAction: List<Action>? = null,
    override var context: ContextData? = null,
    private val balanceToggle: BalanceToggle? = null
) : WidgetView(), ContextComponent, OnInitiableComponent by OnInitiableComponentImpl(onInit),
    BalanceToggleDelegate by BalanceToggleDelegateImpl() {

    override fun buildView(rootView: RootView) = BalanceView(rootView.getContext()).apply {

        rootView.observeBalanceToggle(this)
        rootView.observeBalance(this)
        rootView.observeState(this)

        balanceToggle?.let { rootView.initializeToggleBalanceDelegate(this, it) }

        handleOnInit(rootView, this)
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

    private fun RootView.observeBalanceToggle(view: BalanceView) {
        balanceToggle?.balanceToggleVisible?.let {
            observeBindChanges(
                rootView = this,
                view,
                it
            ) { value ->
                value?.let { isBalanceVisible ->
                    if (isBalanceVisible)
                        view.toggleShowBalance()
                    else
                        view.toggleHideBalance()
                }
            }
        }
    }

    private fun RootView.initializeToggleBalanceDelegate(
        originView: BalanceView,
        balanceToggle: BalanceToggle
    ) {
        initializeToggleDelegate(this, originView, balanceToggle)
    }
}

data class BalanceToggle(
    val onHideAction: List<Action>? = null,
    val onShowAction: List<Action>? = null,
    val balanceToggleVisible: Bind<Boolean>? = null
)

enum class BalanceState {
    LOADING,
    ERROR,
    SUCCESS
}

internal interface BalanceToggleDelegate {

    fun initializeToggleDelegate(
        rootView: RootView,
        originView: BalanceView,
        balanceToggle: BalanceToggle
    )
}

internal class BalanceToggleDelegateImpl : BalanceToggleDelegate, BalanceToggleListener,
    ServerDrivenComponent {

    private lateinit var rootView: RootView
    private lateinit var balanceToggle: BalanceToggle
    private lateinit var balanceView: BalanceView

    override fun initializeToggleDelegate(
        rootView: RootView,
        originView: BalanceView,
        balanceToggle: BalanceToggle
    ) {
        this.balanceView = originView
        this.rootView = rootView
        this.balanceToggle = balanceToggle

        balanceView.setBalanceToggleListener(this)
    }

    override fun toggleOnChange(isVisible: Boolean) {
        if (isVisible) {
            balanceToggle.onShowAction?.forEach {
                it.execute(rootView, balanceView)
            }
        } else {
            balanceToggle.onHideAction?.forEach {
                it.execute(rootView, balanceView)
            }
        }
    }
}



