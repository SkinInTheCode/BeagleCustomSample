package br.com.sknc.beagle.bff.presentation.view

import br.com.sknc.beagle.bff.Color
import br.com.sknc.beagle.bff.TextStyle
import br.com.sknc.beagle.bff.applyMargin
import br.com.sknc.beagle.bff.presentation.widgets.InsightComposedWidget
import br.com.sknc.beagle.bff.presentation.widgets.config.TextConfigData
import br.com.zup.beagle.widget.core.ScrollAxis
import br.com.zup.beagle.widget.layout.Screen
import br.com.zup.beagle.widget.layout.ScrollView
import org.springframework.stereotype.Service

@Service
class SecondHomeScreenBuilder {

    fun build() = Screen(
        id = "SecondHomeScreen",
        child = ScrollView(
            scrollDirection = ScrollAxis.VERTICAL,
            scrollBarEnabled = false,
            children = listOf(
                MainHeaderScreenBuilder.build(),
                InsightComposedWidget(
                    background = Color.Purple_700,
                    imageUrl = "https://nubank.com.br/images-cms/1649356625-ultraviolet-card-floating-lg-3x.png?w=360&dpr=1&auto=compress",
                    title = TextConfigData(
                        text = "Nubank Ultravioleta\nO cartão pensado para quem quer ver além",
                        textStyle = TextStyle.BaseText_Medium_Bold,
                        textColor = Color.White
                    ),
                    button = InsightComposedWidget.Button( title = "saiba mais")
                ).build().applyMargin(top = 24)
            )
        )
    )
}