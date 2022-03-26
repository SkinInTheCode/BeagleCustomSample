package br.com.sknc.beagle.bff.factory

import br.com.sknc.beagle.bff.data.models.MenuItem
import br.com.sknc.beagle.bff.widgets.CircleIconButtonWidget
import br.com.sknc.beagle.bff.widgets.HorizontalListComposedWidget
import br.com.zup.beagle.core.ServerDrivenComponent

object MenuFactory {

    fun build(list: List<MenuItem>) : ServerDrivenComponent {
        val menuItems = mapperMenuItem(list)
        return HorizontalListComposedWidget(menuItems).build()
    }

    private fun mapperMenuItem(list: List<MenuItem>) = list.map {
        CircleIconButtonWidget(
            title = it.title,
            icon = it.icon,
            action = listOf()
        )
    }
}