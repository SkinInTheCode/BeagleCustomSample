package br.com.sknc.beagle.bff.ui.builders

import br.com.sknc.beagle.bff.ButtonWidget
import br.com.sknc.beagle.bff.CustomAction
import br.com.zup.beagle.widget.layout.PageView
import br.com.sknc.beagle.bff.data.models.CrmData
import br.com.sknc.beagle.bff.data.models.CustomActionVictor
import br.com.sknc.beagle.bff.data.models.CustomActionVictor22
import br.com.sknc.beagle.bff.ui.components.BannerComponent
import br.com.zup.beagle.core.*
import br.com.zup.beagle.ext.applyStyle
import br.com.zup.beagle.ext.unitReal
import br.com.zup.beagle.widget.core.EdgeValue
import br.com.zup.beagle.widget.core.Flex
import br.com.zup.beagle.widget.core.Size
import br.com.zup.beagle.widget.layout.ComposeComponent
import br.com.zup.beagle.widget.layout.Container
import br.com.zup.beagle.widget.pager.PageIndicator
import br.com.zup.beagle.widget.pager.PageIndicatorComponent
import br.com.zup.beagle.widget.ui.Button
import javafx.scene.control.Alert

class CarouselBannerBuilder(private val crmData: List<CrmData>) : ComposeComponent {

    override fun build(): ServerDrivenComponent {

        var comp : ServerDrivenComponent

        if (crmData.size > 1){

            comp = Container(
                    children = listOf(
                            PageView(
                                    pageIndicator = PageIndicator(
                                            selectedColor = "#D81B60",
                                            unselectedColor = "#FFF06292"
                                    ),
                                    children = getBannerComponentList()
                            ).apply {
                                Style(
                                        flex = Flex(
                                                grow = 0.0
                                        ),
                                        positionType = PositionType.RELATIVE,
                                        cornerRadius = CornerRadius(30.0)
                                )
                            }
                    )
            ).applyStyle(
                    Style(
                            //size = Size(height = ),
                            flex = Flex(
                                    grow = 0.0
                            ),
                            positionType = PositionType.RELATIVE
                    )
            )

        }else{

            comp =  getBannerComponentList().first()

        }

        return Container(
                children = listOf(
                        ButtonWidget(listOf(CustomActionVictor("Testando Custom Action Victor 1"), CustomActionVictor22("Testando Custom Action Victor 2")), "BFF 99595")
                )
        )


       return comp
    }

    fun getBannerComponentList(): List<BannerComponent> {

        var list = mutableListOf<BannerComponent>()

        for (item in crmData) list.add(BannerComponent(item))

        return list
    }
}