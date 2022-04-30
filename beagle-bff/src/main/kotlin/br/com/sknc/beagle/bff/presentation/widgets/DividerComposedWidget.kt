package br.com.sknc.beagle.bff.presentation.widgets

import br.com.sknc.beagle.bff.unitPercent
import br.com.sknc.beagle.bff.unitReal
import br.com.zup.beagle.ext.setStyle
import br.com.zup.beagle.widget.context.constant
import br.com.zup.beagle.widget.core.Size
import br.com.zup.beagle.widget.layout.ComposeComponent
import br.com.zup.beagle.widget.layout.Container

class DividerComposedWidget(
    private val color: String
) : ComposeComponent {
    override fun build() = Container(
        children = listOf()
    ).setStyle {
        size = Size(width = 100.unitPercent(), height = 2.unitReal())
        backgroundColor = constant( color)
    }

}