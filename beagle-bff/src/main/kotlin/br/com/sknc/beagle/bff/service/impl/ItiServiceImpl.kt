package br.com.sknc.beagle.bff.service.impl

import br.com.sknc.beagle.bff.data.MockDatabase
import org.springframework.stereotype.Service
import br.com.sknc.beagle.bff.service.ItiService
import br.com.sknc.beagle.bff.ui.builders.CarouselQuickActionsBuilder
import br.com.sknc.beagle.bff.ui.builders.ItiHomeBuilder
import br.com.zup.beagle.core.ServerDrivenComponent
import br.com.zup.beagle.widget.layout.Container


@Service
class ItiServiceImpl(private val mockDatabase: MockDatabase) : ItiService {

    override fun getScreenIti() = ItiHomeBuilder()

    override fun getBannerCarouselScreen() = Container( children = listOf())

    override fun getQuickActionsCarouselScreen() = CarouselQuickActionsBuilder().build()

}