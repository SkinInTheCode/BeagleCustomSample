package br.com.sknc.beagle.bff.presentation.widgets.config

import br.com.sknc.beagle.bff.Color
import br.com.sknc.beagle.bff.TextStyle

data class TextConfigData(
    val text: String,
    val textColor: String = Color.Black,
    val textStyle: String = TextStyle.BaseText_Small
)

data class IconTextConfigData(
    val uniCodeIcon: String,
    val color: String = Color.Black,
    val size: Float = 24.0f
)