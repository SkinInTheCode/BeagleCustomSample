package br.com.sknc.beagle.bff.factory

import br.com.sknc.beagle.bff.Color
import br.com.sknc.beagle.bff.data.models.Insight
import br.com.sknc.beagle.bff.widgets.LiveSectionWidget
import br.com.sknc.beagle.bff.widgets.ShimmerWidget
import br.com.zup.beagle.core.CornerRadius
import br.com.zup.beagle.core.ServerDrivenComponent
import br.com.zup.beagle.core.Style
import br.com.zup.beagle.ext.applyStyle
import br.com.zup.beagle.ext.unitPercent
import br.com.zup.beagle.ext.unitReal
import br.com.zup.beagle.widget.action.SendRequest
import br.com.zup.beagle.widget.action.SetContext
import br.com.zup.beagle.widget.context.ContextData
import br.com.zup.beagle.widget.context.expressionOf
import br.com.zup.beagle.widget.context.valueOf
import br.com.zup.beagle.widget.core.Size
import br.com.zup.beagle.widget.layout.Container
import br.com.zup.beagle.widget.navigation.Touchable

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
            url = valueOf("http://10.0.2.2:3000/home/screen/insight"),
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
        ).applyStyle(
            style = Style(
                size = Size(
                    width = 100.unitPercent(),
                    height = 120.unitReal()
                ),
                backgroundColor = Color.Gray,
                cornerRadius = CornerRadius(radius = 12.0)
            )
        )
    )

    private fun buildRetry() = Touchable(
        onPress = listOf(
            SetContext(INSIGHT_CONTEXT_ID, LiveSectionWidget.LiveSectionState.RETRY, "state")
        ),
        child = buildErrorWidget()
    )

    private fun buildErrorWidget() = Container(
    ).applyStyle(
        style = Style(
            size = Size(
                width = 100.unitPercent(),
                height = 120.unitReal()
            ),
            backgroundColor = Color.Black,
            cornerRadius = CornerRadius(radius = 12.0)
        )
    )

}