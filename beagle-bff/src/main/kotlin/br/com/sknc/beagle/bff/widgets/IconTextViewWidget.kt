package br.com.sknc.beagle.bff.widgets

import br.com.zup.beagle.annotation.RegisterWidget
import br.com.zup.beagle.widget.Widget

@RegisterWidget
data class IconTextViewWidget(
    val icon: String,
    val iconColor: String? = null,
    val iconSize: Float? = null
) : Widget()

