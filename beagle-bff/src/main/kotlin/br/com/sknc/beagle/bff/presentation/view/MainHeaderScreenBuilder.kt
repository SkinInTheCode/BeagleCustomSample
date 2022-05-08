package br.com.sknc.beagle.bff.presentation.view

import br.com.sknc.beagle.bff.*
import br.com.sknc.beagle.bff.presentation.widgets.IconTextViewWidget
import br.com.sknc.beagle.bff.presentation.widgets.UserAvatarComposedWidget
import br.com.sknc.beagle.bff.presentation.widgets.config.IconTextConfigData
import br.com.zup.beagle.ext.setStyle
import br.com.zup.beagle.widget.context.constant
import br.com.zup.beagle.widget.core.*
import br.com.zup.beagle.widget.layout.Container
import br.com.zup.beagle.widget.ui.Text

object MainHeaderScreenBuilder {

    fun build(): Container {

        return Container(
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
                                    ),
                                    actionClick = listOf()
                                )
                            )
                        ).setStyle {
                            flex = Flex(
                                alignSelf = AlignSelf.FLEX_START
                            )
                        },
                        Container(
                            children = listOf(
                                buildHeaderIcon(
                                    IconTextConfigData(
                                        uniCodeIcon = "\ue81b",
                                        color = Color.Light_Gray,
                                        size = 20.0f
                                    )
                                ),
                                buildHeaderIcon(
                                    IconTextConfigData(
                                        uniCodeIcon = "\ue83f",
                                        color = Color.Light_Gray,
                                        size = 20.0f
                                    )
                                ),
                                buildHeaderIcon(
                                    IconTextConfigData(
                                        uniCodeIcon = "\ue818",
                                        color = Color.Light_Gray,
                                        size = 20.0f
                                    )
                                )
                            )
                        ).setStyle {
                            flex = Flex(
                                flexDirection = FlexDirection.ROW
                            )
                        }
                    )
                ).setStyle {
                    size = Size(width = 100.unitPercent())
                    flex = Flex(
                        flexDirection = FlexDirection.ROW,
                        justifyContent = JustifyContent.SPACE_BETWEEN,
                    )
                },
                Text(
                    text = "Olá, Victor",
                    textColor = Color.White,
                    styleId = TextStyle.BaseText_Medium_Bold
                ).applyMargin(top = 12, right = 0, left = 0)
            )
        ).setStyle {
            padding = EdgeValue(
                horizontal = 24.unitReal(),
                vertical = 24.unitReal()
            )
            size = Size(
                width = UnitValue.Companion.percent(100)
            )
            backgroundColor = constant(Color.Purple_700)
        }
    }

    private fun buildHeaderIcon(icon: IconTextConfigData) = IconTextViewWidget(
        icon = icon.uniCodeIcon,
        iconColor = icon.color,
        iconSize = icon.size
    ).setStyle {
        size = Size(
            width = 48.unitReal(),
            height = 48.unitReal()
        )
    }
}


