package br.com.sknc.beagle.bff.widgets

import br.com.zup.beagle.annotation.RegisterWidget
import br.com.zup.beagle.core.ServerDrivenComponent
import br.com.zup.beagle.widget.Widget

@RegisterWidget
data class ShimmerWidget(
    val child: ServerDrivenComponent
) : Widget()