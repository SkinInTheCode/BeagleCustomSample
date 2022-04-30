package br.com.sknc.beagle.bff.presentation.widgets

import br.com.sknc.beagle.bff.Color
import br.com.sknc.beagle.bff.presentation.widgets.config.TextConfigData
import br.com.sknc.beagle.bff.unitReal
import br.com.zup.beagle.core.CornerRadius
import br.com.zup.beagle.core.ServerDrivenComponent
import br.com.zup.beagle.ext.setStyle
import br.com.zup.beagle.widget.action.Action
import br.com.zup.beagle.widget.context.constant
import br.com.zup.beagle.widget.core.*
import br.com.zup.beagle.widget.layout.ComposeComponent
import br.com.zup.beagle.widget.layout.Container
import br.com.zup.beagle.widget.navigation.Touchable
import br.com.zup.beagle.widget.ui.Text

class BannerInformationComposedWidget(
    private val first: TextConfigData,
    private val second: TextConfigData,
    private val actionClick: List<Action>,
    private val highlightSecondText: TextConfigData? = null
) : ComposeComponent {

    override fun build() = Container(
        children = listOf(
            Touchable(
                onPress = actionClick,
                child = Container(
                    children = listOf(
                        Text(
                            text = first.text,
                            textColor = first.textColor,
                            styleId = first.textStyle
                        ),
                        buildSecondaryText()
                    )
                )
            )
        )
    ).setStyle {
        size = Size(width = 300.unitReal())
        backgroundColor = constant(Color.Light_Gray)
        cornerRadius = CornerRadius(
            radius = constant(12.0)
        )
        padding = EdgeValue(all = 24.unitReal())
        flex = Flex(
            flexDirection = FlexDirection.COLUMN,
            alignItems = AlignItems.FLEX_START
        )
    }

    private fun buildSecondaryText(): ServerDrivenComponent {
        val secondTextComponent = Text(
            text = second.text,
            textColor = second.textColor,
            styleId = second.textStyle
        )

        return highlightSecondText?.let {
            Container(
                children = listOf(
                    secondTextComponent,
                    Text(
                        text = it.text,
                        textColor = it.textColor,
                        styleId = it.textStyle
                    )
                )
            ).setStyle {
                flex = Flex(
                    flexDirection = FlexDirection.ROW,
                    alignItems = AlignItems.FLEX_START
                )
            }
        } ?: kotlin.run { secondTextComponent }
    }
}