package br.com.sknc.beagle.bff.ui.screenbuilder

import br.com.sknc.beagle.bff.Color
import br.com.sknc.beagle.bff.TextStyle
import br.com.sknc.beagle.bff.applyMargin
import br.com.sknc.beagle.bff.widgets.IconTextViewWidget
import br.com.sknc.beagle.bff.widgets.UserAvatarComposedWidget
import br.com.sknc.beagle.bff.widgets.config.IconTextConfigData
import br.com.zup.beagle.core.Style
import br.com.zup.beagle.ext.applyStyle
import br.com.zup.beagle.ext.unitPercent
import br.com.zup.beagle.ext.unitReal
import br.com.zup.beagle.widget.core.*
import br.com.zup.beagle.widget.layout.Container
import br.com.zup.beagle.widget.ui.Text


object MainHeaderScreenBuilder {

    fun build() = Container(
        children = listOf(
            Container(
                children = listOf(
                    Container(
                        children = listOf(
                            UserAvatarComposedWidget(
                                icon = IconTextConfigData(
                                    uniCodeIcon = "\ue82a",
                                    color = Color.Light_Gray,
                                    size = 20.0f
                                ) ,
                                actionClick = listOf()
                            )
                        )
                    ).applyStyle(
                        Style(
                            flex = Flex(
                                alignSelf = AlignSelf.FLEX_START
                            )
                        )
                    ),
                    Container(
                        children = listOf(
                            buildHeaderIcon(IconTextConfigData(
                                uniCodeIcon = "\ue81b",
                                color = Color.Light_Gray,
                                size = 20.0f
                            )),
                            buildHeaderIcon(IconTextConfigData(
                                uniCodeIcon = "\ue83f",
                                color = Color.Light_Gray,
                                size = 20.0f
                            )),
                            buildHeaderIcon(IconTextConfigData(
                                uniCodeIcon = "\ue818",
                                color = Color.Light_Gray,
                                size = 20.0f
                            ))
                        )
                    ).applyStyle(
                        Style(
                            flex = Flex(
                                flexDirection = FlexDirection.ROW,
                            )
                        )
                    )
                )
            ).applyStyle(
                Style(
                    size = Size(width = 100.unitPercent()),
                    flex = Flex(
                        flexDirection = FlexDirection.ROW,
                        justifyContent = JustifyContent.SPACE_BETWEEN,
                    )
                )
            ),
            Text(
                text = "Olá, Victor",
                textColor = Color.White,
                styleId = TextStyle.BaseText_Medium_Bold
            ).applyMargin(top = 12, right = 0, left = 0)
        )
    ).applyStyle(
        style = Style(
            padding = EdgeValue(
                horizontal = 24.unitReal(),
                vertical = 24.unitReal()
            ),
            size = Size(
                width = 100.unitPercent()
            ),
            backgroundColor = Color.Purple_700
        )
    )

    private fun buildHeaderIcon(icon: IconTextConfigData) = IconTextViewWidget(
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
    )
}