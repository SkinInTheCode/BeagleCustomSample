package br.com.sknc.beagle.bff.widgets

import br.com.sknc.beagle.bff.Color
import br.com.sknc.beagle.bff.widgets.config.IconTextConfigData
import br.com.sknc.beagle.bff.widgets.config.TextConfigData
import br.com.zup.beagle.core.CornerRadius
import br.com.zup.beagle.widget.layout.ComposeComponent
import br.com.zup.beagle.core.Style
import br.com.zup.beagle.ext.applyStyle
import br.com.zup.beagle.ext.unitPercent
import br.com.zup.beagle.ext.unitReal
import br.com.zup.beagle.widget.action.Action
import br.com.zup.beagle.widget.core.*
import br.com.zup.beagle.widget.layout.Container
import br.com.zup.beagle.widget.navigation.Touchable
import br.com.zup.beagle.widget.ui.Text

class BannerButtonComposedWidget(
    private val icon: IconTextConfigData,
    private val title: TextConfigData,
    private val info: TextConfigData,
    private val actionClick: List<Action>
) : ComposeComponent {

    override fun build() = Touchable (
        onPress = actionClick,
        child = Container(
            children = listOf(
                Container(
                    children = listOf(
                        IconTextViewWidget(
                            icon = icon.uniCodeIcon,
                            iconColor = icon.color,
                            iconSize = icon.size
                        ).applyStyle(
                            style = Style(
                                size = Size(
                                    width = 48.unitReal(),
                                    height = 48.unitReal()
                                )
                            )
                        ),
                        Text(
                            text = title.text,
                            textColor = title.textColor,
                            styleId = title.textStyle,
                            alignment = TextAlignment.CENTER
                        ).applyStyle(
                            style = Style(
                                size = Size(
                                    height = 48.unitReal()
                                )
                            )
                        )
                    )
                ).applyStyle(
                    Style(
                        margin = EdgeValue(left = 4.unitReal()),
                        flex = Flex(
                            flexDirection = FlexDirection.ROW
                        )
                    )
                ),
                Text(
                    text = info.text,
                    textColor = info.textColor,
                    styleId = info.textStyle,
                    alignment = TextAlignment.CENTER
                ).applyStyle(
                    Style(
                        size = Size(
                            width = 48.unitReal(),
                            height = 48.unitReal()
                        )
                    )
                )
            )
        ).applyStyle(
            Style(
                size = Size(width = 100.unitPercent()),
                backgroundColor = Color.Light_Gray,
                cornerRadius = CornerRadius(
                    radius = 12.0
                ),
                padding = EdgeValue(vertical = 8.unitReal()),
                flex = Flex(
                    flexDirection = FlexDirection.ROW,
                    justifyContent = JustifyContent.SPACE_BETWEEN,
                    alignItems = AlignItems.CENTER
                )
            )
        )
    )
}