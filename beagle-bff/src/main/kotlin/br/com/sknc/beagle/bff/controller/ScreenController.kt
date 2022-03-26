package br.com.sknc.beagle.bff.controller

import br.com.sknc.beagle.bff.ui.screenbuilder.HomeScreenBuilder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ScreenController(private val screen: HomeScreenBuilder) {

    @GetMapping("home/screen")
    fun getHomeScreen() = screen.build()
}