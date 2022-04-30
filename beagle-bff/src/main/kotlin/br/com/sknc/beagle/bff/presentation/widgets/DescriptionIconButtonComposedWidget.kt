package br.com.sknc.beagle.bff.presentation.widgets

import br.com.sknc.beagle.bff.presentation.widgets.config.IconTextConfigData
import br.com.sknc.beagle.bff.presentation.widgets.config.TextConfigData
import br.com.sknc.beagle.bff.unitPercent
import br.com.sknc.beagle.bff.unitReal
import br.com.zup.beagle.ext.setStyle
import br.com.zup.beagle.widget.action.Action
import br.com.zup.beagle.widget.core.*
import br.com.zup.beagle.widget.layout.ComposeComponent
import br.com.zup.beagle.widget.layout.Container
import br.com.zup.beagle.widget.navigation.Touchable
import br.com.zup.beagle.widget.ui.Text

class DescriptionIconButtonComposedWidget(
    private val title: TextConfigData,
    private val icon: IconTextConfigData,
    private val actionClick: List<Action>
) : ComposeComponent {

    override fun build() = Touchable(
        onPress = actionClick,
        child = Container(
            children = listOf(
                Text(
                    text = title.text,
                    textColor = title.textColor,
                    styleId = title.textStyle
                ),
                IconTextViewWidget(
                    icon = icon.uniCodeIcon,
                    iconColor = icon.color,
                    iconSize = icon.size
                ).setStyle {
                    size = Size(
                        width = 48.unitReal(),
                        height = 48.unitReal()
                    )
                }
            )
        ).setStyle {
            size = Size(width = 100.unitPercent())
            flex = Flex(
                flexDirection = FlexDirection.ROW,
                justifyContent = JustifyContent.SPACE_BETWEEN,
                alignItems = AlignItems.CENTER
            )
        }
    )
}