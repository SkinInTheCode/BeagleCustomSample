package br.com.sknc.beagle.bff.presentation.adapters

import br.com.sknc.beagle.bff.Color
import br.com.sknc.beagle.bff.domain.models.Insight
import br.com.sknc.beagle.bff.presentation.widgets.AnimationWidget
import br.com.sknc.beagle.bff.presentation.widgets.LiveSectionWidget
import br.com.sknc.beagle.bff.presentation.widgets.ShimmerWidget
import br.com.zup.beagle.core.CornerRadius
import br.com.zup.beagle.core.ServerDrivenComponent
import br.com.zup.beagle.ext.setStyle
import br.com.zup.beagle.widget.action.SendRequest
import br.com.zup.beagle.widget.action.SetContext
import br.com.zup.beagle.widget.context.ContextData
import br.com.zup.beagle.widget.context.constant
import br.com.zup.beagle.widget.context.expressionOf
import br.com.zup.beagle.widget.core.Size
import br.com.zup.beagle.widget.core.UnitValue
import br.com.zup.beagle.widget.layout.Container
import br.com.zup.beagle.widget.navigation.Touchable
import com.fasterxml.jackson.databind.util.JSONPObject

object InsightsLiveSectionFactory {

    private const val INSIGHT_CONTEXT_ID = "INSIGHT_CONTEXT_ID"

    fun build(insight: Insight? = null) = LiveSectionWidget(
        state = expressionOf("@{$INSIGHT_CONTEXT_ID.state}"),
        loadStateWidget = buildLoadWidget(),
        errorStateWidget = buildRetry(),
        successWidget = expressionOf("@{$INSIGHT_CONTEXT_ID.successWidget}"),
        context = ContextData(
            INSIGHT_CONTEXT_ID,
            LiveSectionWidget.LiveSectionDataContext(
                state = LiveSectionWidget.LiveSectionState.LOADING
            )
        ),
        onInit = getAction(),
        onRetry = getAction()
    )

    private fun getAction() = listOf(
        SetContext(INSIGHT_CONTEXT_ID, LiveSectionWidget.LiveSectionState.LOADING, "state"),
        SendRequest(
            url = constant("http://10.0.2.2:8080/home/screen/insight"),
            onSuccess = listOf(
                SetContext(
                    INSIGHT_CONTEXT_ID,
                    expressionOf<ServerDrivenComponent>("@{onSuccess.data}"),
                    "successWidget"
                )
            ),
            onError = listOf(
                SetContext(INSIGHT_CONTEXT_ID, LiveSectionWidget.LiveSectionState.ERROR, "state")
            )
        )
    )

    private fun buildLoadWidget() = ShimmerWidget(
        child = Container(
        ).setStyle {
            size = Size(
                width = UnitValue.Companion.percent(100),
                height = UnitValue.Companion.real(120)
            )
            backgroundColor = constant(Color.Gray)
            cornerRadius = CornerRadius(radius = constant(12.0))
        }
    )

    private fun buildRetry() = Touchable(
        onPress = listOf(
            SetContext(INSIGHT_CONTEXT_ID, LiveSectionWidget.LiveSectionState.RETRY, "state")
        ),
        child = buildErrorWidget()
    )


    private fun buildErrorWidget() = Container(
    ).setStyle {
        size = Size(
            width = UnitValue.Companion.percent(100),
            height = UnitValue.Companion.real(120)
        )
        backgroundColor = constant(Color.Black)
        cornerRadius = CornerRadius(radius = constant(12.0))
    }

}