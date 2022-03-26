package br.com.sknc.beagle.bff.factory

import br.com.sknc.beagle.bff.Color
import br.com.sknc.beagle.bff.TextStyle
import br.com.sknc.beagle.bff.data.models.BannerItem
import br.com.sknc.beagle.bff.widgets.BannerInformationComposedWidget
import br.com.sknc.beagle.bff.widgets.HorizontalListComposedWidget
import br.com.sknc.beagle.bff.widgets.config.TextConfigData
import br.com.zup.beagle.core.ServerDrivenComponent

object BannerListFactory {

    fun build(list: List<BannerItem>): ServerDrivenComponent {
        val bannerItems = mapperBannerItem(list)
        return HorizontalListComposedWidget(bannerItems).build()
    }

    private fun mapperBannerItem(list: List<BannerItem>) = list.map {
        BannerInformationComposedWidget(
            first = TextConfigData(text = it.firstText),
            second = TextConfigData(text = it.secondText),
            actionClick = listOf(),
            highlightSecondText = TextConfigData(text = it.secondTextHighlight, textColor = Color.Purple_200, textStyle = TextStyle.BaseText_Small_Bold)
        ).build()
    }

}