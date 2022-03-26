package br.com.sknc.beagle.bff.widgets

import br.com.sknc.beagle.bff.Color
import br.com.sknc.beagle.bff.widgets.config.IconTextConfigData
import br.com.zup.beagle.core.CornerRadius
import br.com.zup.beagle.core.Style
import br.com.zup.beagle.ext.applyStyle
import br.com.zup.beagle.ext.unitReal
import br.com.zup.beagle.widget.action.Action
import br.com.zup.beagle.widget.core.Flex
import br.com.zup.beagle.widget.core.JustifyContent
import br.com.zup.beagle.widget.core.Size
import br.com.zup.beagle.widget.layout.ComposeComponent
import br.com.zup.beagle.widget.layout.Container
import br.com.zup.beagle.widget.navigation.Touchable

class UserAvatarComposedWidget(
    private val icon: IconTextConfigData,
    private val actionClick: List<Action> = listOf()
) : ComposeComponent {

    override fun build() = Touchable(
        onPress = actionClick,
        child = Container(
            children = listOf(
                IconTextViewWidget(
                    icon = icon.uniCodeIcon,
                    iconColor = icon.color,
                    iconSize = icon.size
                )
            )
        ).applyStyle(
            Style(
                size = Size(width = 50.unitReal(), height = 50.unitReal()),
                backgroundColor = Color.Purple_200,
                cornerRadius = CornerRadius(
                    radius = 50.0
                ),
                flex = Flex(
                    justifyContent = JustifyContent.CENTER
                )
            )
        )
    )
}