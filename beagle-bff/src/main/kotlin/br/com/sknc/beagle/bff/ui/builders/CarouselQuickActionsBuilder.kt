package br.com.sknc.beagle.bff.ui.builders

import br.com.zup.beagle.widget.core.ListDirection

import br.com.zup.beagle.widget.layout.Container
import br.com.zup.beagle.widget.ui.ListView
import br.com.sknc.beagle.bff.ui.components.QuickActionComponent


class CarouselQuickActionsBuilder() {

    fun build() = Container(
            children = listOf(
                    ListView(
                            direction = ListDirection.HORIZONTAL,
                            children = getQuickActionComponentList()
                    )
            )
    )

    fun getQuickActionComponentList(): List<QuickActionComponent> {

        var list = mutableListOf<QuickActionComponent>()
  
        return list

    }

}