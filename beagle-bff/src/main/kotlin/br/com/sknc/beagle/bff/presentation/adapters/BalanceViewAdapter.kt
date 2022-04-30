package br.com.sknc.beagle.bff.presentation.adapters

import br.com.sknc.beagle.bff.domain.models.BalanceDataConfig
import br.com.sknc.beagle.bff.presentation.widgets.BalanceContextData
import br.com.sknc.beagle.bff.presentation.widgets.BalanceState
import br.com.sknc.beagle.bff.presentation.widgets.BalanceWidget
import br.com.zup.beagle.widget.Widget
import br.com.zup.beagle.widget.action.SendRequest
import br.com.zup.beagle.widget.action.SetContext
import br.com.zup.beagle.widget.context.ContextData
import br.com.zup.beagle.widget.context.constant
import br.com.zup.beagle.widget.context.expressionOf
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class BalanceViewAdapter {

    private var contextId = ""

    fun build(config: BalanceDataConfig): Widget{
        contextId = UUID.randomUUID().toString()
        return when (config) {
            is BalanceDataConfig.Success -> buildSuccess(config)
            is BalanceDataConfig.Error -> buildError(config)
        }
    }

    private fun buildSuccess(data: BalanceDataConfig.Success) = BalanceWidget(
        viewCycleState = expressionOf("@{global.home.onViewStateChange}"),
        onInit = getAction(data),
        context = ContextData(contextId, BalanceContextData(BalanceState.LOADING, data.balance)),
        state = expressionOf("@{$contextId.state}"),
        balance = expressionOf("@{$contextId.balance}"),
        errorAction = getAction(data)
    )

    private fun buildError(data: BalanceDataConfig.Error) = BalanceWidget(
        viewCycleState = expressionOf("@{global.home.onViewStateChange}"),
        onInit = getAction(data),
        context = ContextData(contextId, BalanceContextData(state = BalanceState.ERROR)),
        state = expressionOf("@{$contextId.state}"),
        balance = expressionOf("@{$contextId.balance}"),
        errorAction = getAction(data)
    )

    private fun getAction(data: BalanceDataConfig) = listOf(
        SetContext(contextId, BalanceState.LOADING, "state"),
        SendRequest(
            url = constant(data.endpoint),
            onSuccess = listOf(
                SetContext(
                    contextId,
                    expressionOf<Double>("@{onSuccess.data.balance}"),
                    "balance"
                ),
                SetContext(contextId, BalanceState.SUCCESS, "state")
            ),
            onError = listOf(
                SetContext(contextId, BalanceState.ERROR, "state")
            )
        )
    )

}