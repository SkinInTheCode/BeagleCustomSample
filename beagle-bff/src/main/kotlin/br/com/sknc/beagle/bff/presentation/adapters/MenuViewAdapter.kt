package br.com.sknc.beagle.bff.presentation.adapters

import br.com.sknc.beagle.bff.domain.models.MenuItem
import br.com.sknc.beagle.bff.presentation.widgets.CircleIconButtonWidget
import br.com.sknc.beagle.bff.presentation.widgets.HorizontalListComposedWidget
import br.com.zup.beagle.core.ServerDrivenComponent

object MenuViewAdapter {

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