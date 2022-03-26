package br.com.sknc.beagle.bff.ui.screenbuilder

import br.com.sknc.beagle.bff.Color
import br.com.sknc.beagle.bff.TextStyle
import br.com.sknc.beagle.bff.applyMargin
import br.com.sknc.beagle.bff.factory.BalanceFactory
import br.com.sknc.beagle.bff.factory.BannerListFactory
import br.com.sknc.beagle.bff.factory.MenuFactory
import br.com.sknc.beagle.bff.repository.BalanceRepository
import br.com.sknc.beagle.bff.repository.BannerRepository
import br.com.sknc.beagle.bff.repository.MenuRepository
import br.com.sknc.beagle.bff.widgets.*
import br.com.sknc.beagle.bff.widgets.config.IconTextConfigData
import br.com.sknc.beagle.bff.widgets.config.TextConfigData
import br.com.zup.beagle.core.Style
import br.com.zup.beagle.ext.applyStyle
import br.com.zup.beagle.ext.unitReal
import br.com.zup.beagle.widget.core.ScrollAxis
import br.com.zup.beagle.widget.core.Size
import br.com.zup.beagle.widget.layout.Screen
import br.com.zup.beagle.widget.layout.ScrollView
import br.com.zup.beagle.widget.ui.Text
import org.springframework.stereotype.Service

@Service
class HomeScreenBuilder(
    private val menuRepository: MenuRepository,
    private val balanceRepository: BalanceRepository,
    private val bannerRepository: BannerRepository,
) {

    fun build() = Screen(
        identifier = "HomeScreen",
        child = ScrollView(
            scrollDirection = ScrollAxis.VERTICAL,
            scrollBarEnabled = false,
            children = listOf(
                MainHeaderScreenBuilder.build(),
                DescriptionIconButtonComposedWidget(
                    title = TextConfigData(text = "Conta", textStyle = TextStyle.BaseText_Medium_Bold),
                    icon = IconTextConfigData(uniCodeIcon = "\ue876", size = 16.0f),
                    actionClick = listOf()
                ).applyMargin(top = 12),
                BalanceFactory.build(
                    BalanceFactory.BalanceDataConfig(
                        balance = balanceRepository.getBalance(),
                        contextId = "balanceContextId",
                        url = "http://10.0.2.2:3000/home/data/balance"
                    )
                ).applyMargin(bottom = 24),
                MenuFactory.build(menuRepository.getMenu()).applyMargin(bottom = 24, left = 0, right = 0),
                BannerButtonComposedWidget(
                    IconTextConfigData("\ue82f"),
                    TextConfigData("Meus cartões"),
                    TextConfigData("3"),
                    listOf()
                ).build().applyMargin(bottom = 24),
                BannerListFactory.build(bannerRepository.getBanners()).applyMargin(bottom = 24, left = 0, right = 0),
                DividerComposedWidget(Color.Light_Gray).build().applyMargin(bottom = 12, left = 0, right = 0),
                IconTextViewWidget(
                    icon = "\ue871",
                    iconColor = Color.Black,
                    iconSize = 24.0f
                ).applyStyle(
                    style = Style(
                        size = Size(
                            width = 40.unitReal(),
                            height = 40.unitReal()
                        )
                    )
                ).applyMargin(left = 12),
                DescriptionIconButtonComposedWidget(
                    title = TextConfigData(text = "Cartão de crédito", textStyle = TextStyle.BaseText_Medium_Bold),
                    icon = IconTextConfigData(uniCodeIcon = "\ue876", size = 16.0f),
                    actionClick = listOf()
                ).applyMargin(),
                Text(
                    text = "Fatura atual",
                    textColor = Color.Gray,
                    styleId = TextStyle.BaseText_Small_Bold
                ).applyMargin(),
                BalanceFactory.build(
                    BalanceFactory.BalanceDataConfig(
                        balance = balanceRepository.getCreditCardBalance(),
                        contextId = "creditCardBalanceContextId",
                        url = "http://10.0.2.2:3000/home/data/creditcard"
                    )
                ).applyMargin(bottom = 24)
            )
        )
    )
}