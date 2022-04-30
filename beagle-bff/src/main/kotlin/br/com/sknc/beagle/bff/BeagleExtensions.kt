package br.com.sknc.beagle.bff

import br.com.zup.beagle.core.ServerDrivenComponent
import br.com.zup.beagle.core.Style
import br.com.zup.beagle.ext.setStyle
import br.com.zup.beagle.widget.Widget
import br.com.zup.beagle.widget.core.EdgeValue
import br.com.zup.beagle.widget.core.UnitValue
import br.com.zup.beagle.widget.layout.Container

fun Int.unitReal() = UnitValue.Companion.real(this)
fun Int.unitPercent() = UnitValue.Companion.percent(this)

fun ServerDrivenComponent.applyMargin(left: Int = 24, top: Int = 0, right: Int = 24, bottom: Int = 0) =
    Container(
        children = listOf(this)
    ).setStyle {
        margin = EdgeValue(
            left = UnitValue.Companion.real(left),
            top = UnitValue.Companion.real(top),
            right = UnitValue.Companion.real(right),
            bottom = UnitValue.Companion.real(bottom)
        )
    }

fun Widget.applyListHorizontalMargin(marginLeft: Int, marginRight: Int) =
    this.apply {
        this.style = (style ?: Style()).copy(
            margin = EdgeValue(
                left = UnitValue.Companion.real(marginLeft) ,
                right = UnitValue.Companion.real(marginRight)
            )
        )
    }