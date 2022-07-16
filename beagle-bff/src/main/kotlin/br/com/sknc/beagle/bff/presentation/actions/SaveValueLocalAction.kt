package br.com.sknc.beagle.bff.presentation.actions

import br.com.zup.beagle.analytics.ActionAnalyticsConfig
import br.com.zup.beagle.annotation.RegisterAction
import br.com.zup.beagle.widget.action.Action
import br.com.zup.beagle.widget.action.AnalyticsAction
import br.com.zup.beagle.widget.context.Bind

@RegisterAction
data class RetrieveValueLocalAction(
    val key: String,
    val onValue: List<Action>,
    val onNullOrEmpty: List<Action>? = null,
    val onError: List<Action>? = null,
    override var analytics: ActionAnalyticsConfig? = null
) : AnalyticsAction

@RegisterAction
class SaveValueLocalAction(
    val key: String,
    val value: Bind<Any>,
    override var analytics: ActionAnalyticsConfig? = null
) : AnalyticsAction