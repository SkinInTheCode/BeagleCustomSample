package br.com.sknc.beagle.bff.widgets

import br.com.zup.beagle.core.Style
import br.com.zup.beagle.ext.applyStyle
import br.com.zup.beagle.ext.unitPercent
import br.com.zup.beagle.ext.unitReal
import br.com.zup.beagle.widget.core.Size
import br.com.zup.beagle.widget.layout.ComposeComponent
import br.com.zup.beagle.widget.layout.Container

class DividerComposedWidget(
    private val color: String
) : ComposeComponent {
    override fun build() = Container(
        children = listOf()
    ).applyStyle(
        Style(
            size = Size(width = 100.unitPercent(), height = 2.unitReal()),
            backgroundColor = color
        )
    )
}