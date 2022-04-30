package br.com.sknc.beagle.bff.presentation.adapters

import br.com.sknc.beagle.bff.Color
import br.com.sknc.beagle.bff.TextStyle
import br.com.sknc.beagle.bff.domain.models.BannerItem
import br.com.sknc.beagle.bff.presentation.widgets.BannerInformationComposedWidget
import br.com.sknc.beagle.bff.presentation.widgets.HorizontalListComposedWidget
import br.com.sknc.beagle.bff.presentation.widgets.config.TextConfigData
import br.com.zup.beagle.core.ServerDrivenComponent

object BannerListViewAdapter {

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