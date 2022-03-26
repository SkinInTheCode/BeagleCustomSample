package br.com.sknc.beagle.bff

import br.com.zup.beagle.core.ServerDrivenComponent
import br.com.zup.beagle.core.Style
import br.com.zup.beagle.ext.applyStyle
import br.com.zup.beagle.ext.unitReal
import br.com.zup.beagle.widget.Widget
import br.com.zup.beagle.widget.core.EdgeValue
import br.com.zup.beagle.widget.layout.Container

fun ServerDrivenComponent.applyMargin(left: Int = 24, top: Int = 0, right: Int = 24, bottom: Int = 0) =
    Container(
        children = listOf(this)
    ).applyStyle(
        Style(
            margin = EdgeValue(
                left = left.unitReal(),
                top = top.unitReal(),
                right = right.unitReal(),
                bottom = bottom.unitReal()
            )
        )
    )

fun Widget.applyListHorizontalMargin(marginLeft: Int, marginRight: Int) =
    this.apply {
        this.style = (style ?: Style()).copy(
            margin = EdgeValue(
                left = marginLeft.unitReal(),
                right = marginRight.unitReal()
            )
        )
    }