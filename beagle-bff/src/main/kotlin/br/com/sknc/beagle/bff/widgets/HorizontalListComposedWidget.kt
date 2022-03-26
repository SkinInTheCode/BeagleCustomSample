package br.com.sknc.beagle.bff.widgets

import br.com.sknc.beagle.bff.applyListHorizontalMargin
import br.com.zup.beagle.widget.Widget
import br.com.zup.beagle.widget.core.*
import br.com.zup.beagle.widget.layout.ComposeComponent
import br.com.zup.beagle.widget.layout.Container
import br.com.zup.beagle.widget.layout.ScrollView

class HorizontalListComposedWidget(
    val list: List<Widget>
) : ComposeComponent {

    override fun build() = Container(
        children = listOf(
            ScrollView(
                scrollDirection = ScrollAxis.HORIZONTAL,
                scrollBarEnabled = false,
                children = list.mapIndexed { index, component ->
                    when (index) {
                        0 -> component.applyListHorizontalMargin(24, 8)
                        list.lastIndex -> component.applyListHorizontalMargin(8, 24)
                        else -> component.applyListHorizontalMargin(8, 8)
                    }
                }
            )
        )
    )
}

