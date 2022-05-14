package br.com.sknc.beagle.bff.presentation.widgets

import br.com.zup.beagle.annotation.RegisterWidget
import br.com.zup.beagle.widget.Widget
import com.fasterxml.jackson.databind.util.JSONPObject

@RegisterWidget
class AnimationWidget(
    val type : Type,
    val value: String,
    val repeatCount : Int
) : Widget() {

    enum class Type {
        JSON,
        TOKEN
    }
}