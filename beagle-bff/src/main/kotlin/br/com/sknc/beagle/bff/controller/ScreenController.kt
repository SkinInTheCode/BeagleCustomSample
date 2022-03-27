package br.com.sknc.beagle.bff.controller

import br.com.sknc.beagle.bff.Color
import br.com.sknc.beagle.bff.ui.screenbuilder.HomeScreenBuilder
import br.com.zup.beagle.core.CornerRadius
import br.com.zup.beagle.core.Style
import br.com.zup.beagle.ext.applyStyle
import br.com.zup.beagle.ext.unitPercent
import br.com.zup.beagle.ext.unitReal
import br.com.zup.beagle.widget.core.Size
import br.com.zup.beagle.widget.layout.Container
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ScreenController(private val screen: HomeScreenBuilder) {

    @GetMapping("home/screen")
    fun getHomeScreen() = screen.build()

    @GetMapping("home/screen/insight")
    fun getInsight() = Container(
    ).applyStyle(
        style = Style(
            size = Size(
                width = 100.unitPercent(),
                height = 120.unitReal()
            ),
            backgroundColor = Color.Purple_700,
            cornerRadius = CornerRadius(radius = 12.0)
        )
    )

}