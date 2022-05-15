package br.com.sknc.beagle.bff.presentation.adapters

import br.com.sknc.beagle.bff.domain.models.BalanceDataConfig
import br.com.sknc.beagle.bff.presentation.widgets.BalanceContextData
import br.com.sknc.beagle.bff.presentation.widgets.BalanceState
import br.com.sknc.beagle.bff.presentation.widgets.BalanceWidget
import br.com.sknc.beagle.bff.presentation.widgets.observer.ServerDrivenLifeCycleObserver
import br.com.zup.beagle.widget.Widget
import br.com.zup.beagle.widget.action.SendRequest
import br.com.zup.beagle.widget.action.SetContext
import br.com.zup.beagle.widget.context.ContextData
import br.com.zup.beagle.widget.context.constant
import br.com.zup.beagle.widget.context.expressionOf
import org.springframework.stereotype.Service

@Service
class BalanceViewAdapter {

    fun build(config: BalanceDataConfig): Widget {
        return when (config) {
            is BalanceDataConfig.Success -> buildSuccess(config, config.identifier).wrapperLifeCycleObserve(config)
            is BalanceDataConfig.Error -> buildError(config, config.identifier).wrapperLifeCycleObserve(config)
        }
    }

    private fun BalanceWidget.wrapperLifeCycleObserve(data: BalanceDataConfig) = ServerDrivenLifeCycleObserver(
        context = ContextData(id = data.identifier, BalanceContextData(BalanceState.LOADING)),
        child = this,
        onViewShow = getAction(data, data.identifier)
    )

    private fun buildSuccess(data: BalanceDataConfig.Success, contextId: String): BalanceWidget {
        return BalanceWidget(
            state = expressionOf("@{$contextId.state}"),
            balance = expressionOf("@{$contextId.balance}"),
            errorAction = getAction(data, contextId)
        )
    }

    private fun buildError(data: BalanceDataConfig.Error, contextId: String) = BalanceWidget(
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