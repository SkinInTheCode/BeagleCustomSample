package com.example.serverdriven.actions

import android.view.View
import br.com.zup.beagle.android.action.*
import br.com.zup.beagle.android.analytics.ActionAnalyticsConfig
import br.com.zup.beagle.android.annotation.ContextDataValue
import br.com.zup.beagle.android.annotation.RegisterAction
import br.com.zup.beagle.android.context.Bind
import br.com.zup.beagle.android.context.ContextComponent
import br.com.zup.beagle.android.context.ContextData
import br.com.zup.beagle.android.utils.evaluateExpression
import br.com.zup.beagle.android.utils.handleEvent
import br.com.zup.beagle.android.widget.RootView
import com.example.serverdriven.ServerDrivenInitializer
import java.lang.Exception

@RegisterAction
class RetrieveValueLocalAction(
    private val key: String,
    private val onValue: List<Action>,
    private val onNullOrEmpty: List<Action>? = null,
    private val onError: List<Action>? = null,
    override var analytics: ActionAnalyticsConfig? = null,
) : AnalyticsAction, AsyncAction by AsyncActionImpl() {

    override fun execute(rootView: RootView, origin: View) {
        try {
            ServerDrivenInitializer.environment.storage.retrieve(key, Any::class.java)
                .let { value ->
                    value.takeIf { it != null && checkIfIsEmpty(it) }?.let {
                        handleEvent(
                            rootView,
                            origin,
                            onValue,
                            ContextData("onValue", it),
                            analyticsValue = "RetrieveValueLocalAction_onValue"
                        )
                    } ?: run {
                        onNullOrEmpty?.let {
                            handleEvent(
                                rootView,
                                origin,
                                it,
                                null,
                                analyticsValue = "RetrieveValueLocalAction_onNullOrEmpty"
                            )
                        }
                    }
                }
        } catch (e: Exception) {
            onError?.let {
                handleEvent(
                    rootView,
                    origin,
                    it,
                    ContextData("onError", e),
                    analyticsValue = "RetrieveValueLocalAction_onError"
                )
            }
        }
    }

    private fun checkIfIsEmpty(value: Any) = if (value is String) value.isNotEmpty() else true
}

@RegisterAction
class SaveValueLocalAction(
    private val key: String,
    private val value: Bind<Any>,
    override var analytics: ActionAnalyticsConfig? = null
) : AnalyticsAction {

    override fun execute(rootView: RootView, origin: View) {
        evaluateExpression(rootView, origin, this.value)?.let {
            ServerDrivenInitializer.environment.storage.save(key, it)
        }
    }
}