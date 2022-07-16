package br.com.sknc.beagle.bff.presentation.view

import br.com.sknc.beagle.bff.Color
import br.com.sknc.beagle.bff.TextStyle
import br.com.sknc.beagle.bff.applyMargin
import br.com.sknc.beagle.bff.data.repository.BalanceRepository
import br.com.sknc.beagle.bff.presentation.adapters.BalanceViewAdapter
import br.com.sknc.beagle.bff.presentation.adapters.BannerListViewAdapter
import br.com.sknc.beagle.bff.presentation.adapters.InsightsLiveSectionFactory
import br.com.sknc.beagle.bff.presentation.adapters.MenuViewAdapter
import br.com.sknc.beagle.bff.data.repository.BannerRepository
import br.com.sknc.beagle.bff.data.repository.MenuRepository
import br.com.sknc.beagle.bff.data.service.BalanceService
import br.com.sknc.beagle.bff.data.service.MenuService
import br.com.sknc.beagle.bff.domain.models.BalanceDataConfig
import br.com.sknc.beagle.bff.presentation.widgets.*
import br.com.sknc.beagle.bff.presentation.widgets.config.IconTextConfigData
import br.com.sknc.beagle.bff.presentation.widgets.config.TextConfigData
import br.com.zup.beagle.ext.setStyle
import br.com.zup.beagle.widget.core.ScrollAxis
import br.com.zup.beagle.widget.core.Size
import br.com.zup.beagle.widget.core.UnitValue
import br.com.zup.beagle.widget.layout.Screen
import br.com.zup.beagle.widget.layout.ScrollView
import br.com.zup.beagle.widget.ui.Text
import org.springframework.stereotype.Service

@Service
class HomeScreenBuilder(
    private val menuService: MenuService,
    private val menuRepository: MenuRepository,
    private val balanceService: BalanceService,
    private val bannerRepository: BannerRepository,
    private val balanceViewAdapter: BalanceViewAdapter,
    private val balanceRepository: BalanceRepository
) {

    //fun build() = balanceViewAdapter.build(balanceService.getBalanceSectionData()).applyMargin( top = 24, bottom = 24)

    fun build() = Screen(
        id = "HomeScreen",
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
                    button = InsightComposedWidget.Button(title = "saiba mais")
                ).build().applyMargin(top = 24),
                InsightsLiveSectionFactory.build().applyMargin(top = 24, bottom = 12),
                DescriptionIconButtonComposedWidget(
                    title = TextConfigData(text = "Conta", textStyle = TextStyle.BaseText_Medium_Bold),
                    icon = IconTextConfigData(uniCodeIcon = "\ue876", size = 16.0f),
                    actionClick = listOf()
                ).applyMargin(top = 12),
                balanceViewAdapter.build(balanceService.getBalanceSectionData()).applyMargin(bottom = 24),
                MenuViewAdapter.build(menuRepository.fetchMenu()).applyMargin(bottom = 24, left = 0, right = 0),
                BannerButtonComposedWidget(
                    IconTextConfigData("\ue82f"),
                    TextConfigData("Meus cartões", textStyle = TextStyle.BaseText_Small_Bold),
                    TextConfigData("3", textStyle = TextStyle.BaseText_Small_Bold),
                    listOf()
                ).build().applyMargin(bottom = 24),
                BannerListViewAdapter.build(bannerRepository.getBanners())
                    .applyMargin(bottom = 24, left = 0, right = 0),
                DividerComposedWidget(Color.Light_Gray).build().applyMargin(bottom = 12, left = 0, right = 0),
                IconTextViewWidget(
                    icon = "\ue871",
                    iconColor = Color.Black,
                    iconSize = 24.0f
                ).setStyle {
                    size = Size(
                        width = UnitValue.Companion.real(40),
                        height = UnitValue.Companion.real(40)
                    )
                }.applyMargin(left = 12),
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
                balanceViewAdapter.build(
                    BalanceDataConfig.Success(
                        balance = balanceRepository.getCreditCardBalance(),
                        endpoint = "http://10.0.2.2:3000/home/data/creditcard"
                    )
                ).applyMargin(bottom = 24)
            )
        )
    )
}