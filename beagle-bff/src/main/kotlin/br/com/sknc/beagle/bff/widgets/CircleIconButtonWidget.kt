package br.com.sknc.beagle.bff.widgets

import br.com.zup.beagle.annotation.RegisterWidget
import br.com.zup.beagle.widget.Widget
import javax.swing.Action

@RegisterWidget
data class CircleIconButtonWidget(
    val title: String,
    val icon: String,
    val backgroundColor: String? = null,
    val action: List<Action>
) : Widget()