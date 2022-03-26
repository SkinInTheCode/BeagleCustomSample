package br.com.sknc.beagle.bff.factory

import br.com.sknc.beagle.bff.widgets.BalanceData
import br.com.sknc.beagle.bff.widgets.BalanceState
import br.com.sknc.beagle.bff.widgets.BalanceWidget
import br.com.zup.beagle.widget.action.SendRequest
import br.com.zup.beagle.widget.action.SetContext
import br.com.zup.beagle.widget.context.ContextData
import br.com.zup.beagle.widget.context.expressionOf
import br.com.zup.beagle.widget.context.valueOf

object BalanceFactory {

    data class BalanceDataConfig(
        val balance: Double,
        val contextId: String,
        val url: String = "",
        val state: BalanceState = BalanceState.LOADING
    )

    fun build(data: BalanceDataConfig) = BalanceWidget(
        viewCycleState = expressionOf("@{global.home.onViewStateChange}"),
        onInit = getAction(data),
        context = ContextData(data.contextId, BalanceData(data.state, data.balance)),
        state = expressionOf("@{${data.contextId}.state}"),
        balance = expressionOf("@{${data.contextId}.balance}"),
        errorAction = getAction(data)
    )

    private fun getAction(data: BalanceDataConfig) = listOf(
        SetContext(data.contextId, data.state, "state"),
        SendRequest(
            url = valueOf(data.url),
            onSuccess = listOf(
                SetContext(
                    data.contextId,
                    expressionOf<Double>("@{onSuccess.data.balance}"),
                    "balance"
                ),
                SetContext(data.contextId, BalanceState.SUCCESS, "state")
            ),
            onError = listOf(
                SetContext(data.contextId, BalanceState.ERROR, "state")
            )
        )
    )

}