package br.com.sknc.beagle.bff.controller

import br.com.sknc.beagle.bff.Color
import br.com.sknc.beagle.bff.presentation.view.HomeScreenBuilder
import br.com.zup.beagle.core.CornerRadius
import br.com.zup.beagle.ext.setStyle
import br.com.zup.beagle.widget.context.constant
import br.com.zup.beagle.widget.core.Size
import br.com.zup.beagle.widget.core.UnitValue
import br.com.zup.beagle.widget.layout.Container
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ScreenController(private val screen: HomeScreenBuilder) {

    @GetMapping("home/screen")
    fun getHomeScreen() = screen.build()

    @GetMapping("home/screen/insight")
    fun getInsight() = Container(
    ).setStyle {
        size = Size(
            width = UnitValue.Companion.percent(100),
            height = UnitValue.Companion.real(120)
        )
        backgroundColor = constant(Color.Purple_700)
        cornerRadius = CornerRadius(radius = constant(12.0))
    }

}