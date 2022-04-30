package br.com.sknc.beagle.bff.presentation.widgets

import br.com.sknc.beagle.bff.Color
import br.com.sknc.beagle.bff.presentation.widgets.config.IconTextConfigData
import br.com.sknc.beagle.bff.presentation.widgets.config.TextConfigData
import br.com.sknc.beagle.bff.unitPercent
import br.com.sknc.beagle.bff.unitReal
import br.com.zup.beagle.core.CornerRadius
import br.com.zup.beagle.widget.layout.ComposeComponent
import br.com.zup.beagle.ext.setStyle
import br.com.zup.beagle.widget.action.Action
import br.com.zup.beagle.widget.context.constant
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
                        ).setStyle {
                            size = Size(
                                width = 48.unitReal(),
                                height = 48.unitReal()
                            )
                        },
                        Text(
                            text = title.text,
                            textColor = title.textColor,
                            styleId = title.textStyle,
                            alignment = TextAlignment.CENTER
                        ).setStyle {
                            size = Size(
                                height = 48.unitReal()
                            )
                        }
                    )
                ).setStyle {
                    margin = EdgeValue(left = 4.unitReal())
                    flex = Flex(
                        flexDirection = FlexDirection.ROW
                    )
                },
                Text(
                    text = info.text,
                    textColor = info.textColor,
                    styleId = info.textStyle,
                    alignment = TextAlignment.CENTER
                ).setStyle{
                    size = Size(
                        width = 48.unitReal(),
                        height = 48.unitReal()
                    )
                }
            )
        ).setStyle {
            size = Size(width = 100.unitPercent())
            backgroundColor = constant(Color.Light_Gray)
            cornerRadius = CornerRadius(
                radius = constant(12.0)
            )
            padding = EdgeValue(vertical = 8.unitReal())
            flex = Flex(
                flexDirection = FlexDirection.ROW,
                justifyContent = JustifyContent.SPACE_BETWEEN,
                alignItems = AlignItems.CENTER
            )
        }
    )
}