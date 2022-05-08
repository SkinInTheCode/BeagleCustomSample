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

    fun build(config: BalanceDataConfig): Widget {

        return when (config) {
            is BalanceDataConfig.Success -> buildSuccess(config, UUID.randomUUID().toString())
            is BalanceDataConfig.Error -> buildError(config, UUID.randomUUID().toString())
        }
    }

    private fun buildSuccess(data: BalanceDataConfig.Success, contextId: String): Widget {
        return BalanceWidget(
            viewCycleState = expressionOf("@{global.home.onViewStateChange}"),
            onInit = getAction(data, contextId),
            context = ContextData(contextId, BalanceContextData(BalanceState.LOADING, data.balance)),
            state = expressionOf("@{$contextId.state}"),
            balance = expressionOf("@{$contextId.balance}"),
            errorAction = getAction(data, contextId)
        )
    }

    private fun buildError(data: BalanceDataConfig.Error, contextId: String) = BalanceWidget(
        viewCycleState = expressionOf("@{global.home.onViewStateChange}"),
        onInit = getAction(data, contextId),
        context = ContextData(contextId, BalanceContextData(state = BalanceState.ERROR)),
        state = expressionOf("@{$contextId.state}"),
        balance = expressionOf("@{$contextId.balance}"),
        errorAction = getAction(data, contextId)
    )

    private fun getAction(data: BalanceDataConfig, contextId: String) = listOf(
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