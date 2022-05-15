package br.com.sknc.beagle.bff.presentation.widgets

import br.com.sknc.beagle.bff.Color
import br.com.sknc.beagle.bff.applyMargin
import br.com.sknc.beagle.bff.presentation.widgets.config.TextConfigData
import br.com.zup.beagle.core.CornerRadius
import br.com.zup.beagle.core.PositionType
import br.com.zup.beagle.ext.setStyle
import br.com.zup.beagle.widget.action.Action
import br.com.zup.beagle.widget.context.constant
import br.com.zup.beagle.widget.core.*
import br.com.zup.beagle.widget.layout.ComposeComponent
import br.com.zup.beagle.widget.layout.Container
import br.com.zup.beagle.widget.ui.Button
import br.com.zup.beagle.widget.ui.Image
import br.com.zup.beagle.widget.ui.ImagePath
import br.com.zup.beagle.widget.ui.Text

class InsightComposedWidget(
    val title: TextConfigData,
    val button : Button,
    val imageUrl : String,
    val background : String
): ComposeComponent {

    data class Button(val title: String, val actionClick: List<Action>? = null)

    override fun build() = Container(

        children = listOf (
            Container(
                listOf(
                    Text(
                        text = title.text,
                        textColor = title.textColor,
                        styleId = title.textStyle
                    ),
                    Button(
                        text = button.title,
                        onPress = button.actionClick
                    ).setStyle {
                        size = Size(width = UnitValue.Companion.percent(75))
                        cornerRadius = CornerRadius(
                            radius = constant(12.0)
                        )
                        margin = EdgeValue(top = UnitValue.Companion.real(4))
                    }
                )
            ).setStyle {
               size = Size(width = UnitValue.Companion.percent(75))
            },
            Image(
                path = ImagePath.Remote(remoteUrl = imageUrl)
            ).setStyle {
                positionType = PositionType.ABSOLUTE
                position = EdgeValue(right = UnitValue.Companion.real(24), bottom = UnitValue.Companion.real(24))
                size = Size(height = UnitValue.Companion.real(150), width = UnitValue.Companion.real(150))
            }
        )
    ).setStyle {
        backgroundColor = constant(background)
        cornerRadius = CornerRadius(
            radius = constant(12.0)
        )
        size = Size(height = UnitValue.Companion.real(350))
        padding = EdgeValue.all(all = 24)
    }
}

