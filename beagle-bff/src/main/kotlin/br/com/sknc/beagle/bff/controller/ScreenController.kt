package br.com.sknc.beagle.bff.controller

import br.com.sknc.beagle.bff.Color
import br.com.sknc.beagle.bff.presentation.view.HomeScreenBuilder
import br.com.sknc.beagle.bff.presentation.widgets.AnimationWidget
import br.com.zup.beagle.core.CornerRadius
import br.com.zup.beagle.ext.setStyle
import br.com.zup.beagle.widget.context.constant
import br.com.zup.beagle.widget.core.*
import br.com.zup.beagle.widget.layout.Container
import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.io.File

@RestController
class ScreenController(private val screen: HomeScreenBuilder) {

    @Value("\${animation.bear}")
    lateinit var animationBear: String

    @GetMapping("home/screen")
    fun getHomeScreen() = screen.build()

    @GetMapping("home/screen/insight")
    fun getInsight() = Container(
        children = listOf(
            AnimationWidget(
                type = AnimationWidget.Type.JSON,
                value = animationBear,
                repeatCount = -1
            ).setStyle {
                size = Size(
                    width = UnitValue.Companion.percent(40),
                    height = UnitValue.Companion.real(120)
                )
                flex = Flex(
                    alignSelf = AlignSelf.FLEX_END
                )
            }
        )
    ).setStyle {
        size = Size(
            width = UnitValue.Companion.percent(100),
            height = UnitValue.Companion.real(120)
        )
        backgroundColor = constant(Color.Light_Gray)
        cornerRadius = CornerRadius(radius = constant(12.0))
    }

}

fun readFileDirectlyAsText(fileName: String): String
        = File(fileName).readText(Charsets.UTF_8)