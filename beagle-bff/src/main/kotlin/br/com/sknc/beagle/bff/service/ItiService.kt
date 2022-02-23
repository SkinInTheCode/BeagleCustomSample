package br.com.sknc.beagle.bff.service

import br.com.sknc.beagle.bff.ui.builders.ItiHomeBuilder
import br.com.zup.beagle.core.ServerDrivenComponent
import br.com.zup.beagle.widget.layout.Container
import br.com.zup.beagle.widget.layout.PageView

interface ItiService {

    fun getScreenIti() : ItiHomeBuilder

    fun getBannerCarouselScreen() : ServerDrivenComponent

    fun getQuickActionsCarouselScreen() : Container

}