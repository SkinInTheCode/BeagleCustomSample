package com.example.beaglecustomsamplesandroid

import android.os.Bundle
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import br.com.zup.beagle.android.action.Action
import br.com.zup.beagle.android.action.SendRequest
import br.com.zup.beagle.android.action.SetContext
import br.com.zup.beagle.android.components.Text
import br.com.zup.beagle.android.components.layout.Container
import br.com.zup.beagle.android.components.layout.Screen
import br.com.zup.beagle.android.components.layout.ScrollView
import br.com.zup.beagle.android.context.ContextData
import br.com.zup.beagle.android.context.expressionOf
import br.com.zup.beagle.android.context.valueOf
import br.com.zup.beagle.android.networking.HttpAdditionalData
import br.com.zup.beagle.android.networking.HttpMethod
import br.com.zup.beagle.android.networking.RequestData
import br.com.zup.beagle.android.utils.loadView
import br.com.zup.beagle.android.utils.toView
import br.com.zup.beagle.android.view.ServerDrivenState
import br.com.zup.beagle.android.view.custom.OnServerStateChanged
import br.com.zup.beagle.core.CornerRadius
import br.com.zup.beagle.core.ServerDrivenComponent
import br.com.zup.beagle.core.Style
import br.com.zup.beagle.ext.applyFlex
import br.com.zup.beagle.ext.applyStyle
import br.com.zup.beagle.ext.unitPercent
import br.com.zup.beagle.ext.unitReal
import br.com.zup.beagle.widget.Widget
import br.com.zup.beagle.widget.core.*
import com.example.serverdriven.ViewCycleListener
import com.example.serverdriven.components.balance.widgets.BalanceState
import com.example.serverdriven.components.balance.widgets.BalanceWidget
import com.example.serverdriven.onViewStateChange
import com.example.serverdriven.widgets.CircleIconButtonWidget
import com.example.serverdriven.widgets.IconTextViewWidget

class MainActivity : AppCompatActivity() {

    private val flBalance by lazy { findViewById<FrameLayout>(R.id.flBalance) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(buildScreen())
        //loadLocalComponent()
        //loadFromServer()
    }

    override fun onResume() {
        super.onResume()

        onViewStateChange("home", ViewCycleListener.ViewState.RESUME)
    }

    data class BalanceData(val state: BalanceState, val balance: Double)

    private fun buildScreen() =
        Screen(
            id = "MainScreen",
            child = ScrollView(
                scrollDirection = ScrollAxis.VERTICAL,
                scrollBarEnabled = false,
                children = listOf(
                    buildHeader(),
                    buildDescriptionIconButton("Conta").applyMargin(top = 12),
                    buildBalance().applyMargin(bottom = 24),
                    buildMenuList(getMenuList()).applyMargin(bottom = 24, left = 0, right = 0),
                    buildBannerButton("\ue82f", "Meus cartões", "3").applyMargin(bottom = 24),
                    buildBannerList(
                        listOf(
                            buildBanner("Você tem R$ 5.000,00", "disponíveis para empréstimo."),
                            buildBanner("Tem SHOPPING no seu bank.", "Começe agora."),
                            buildBanner(
                                "Conheça a conta Pj: prática e",
                                "livre de burocracia para o seu negócio."
                            )
                        )
                    ).applyMargin(bottom = 24, left = 0, right = 0),
                    buildDivider("#F0F1F5").applyMargin(bottom = 12, left = 0, right = 0),
                    IconTextViewWidget(
                        icon = "\ue871",
                        iconColor = "#000000",
                        iconSize = 24.0f
                    ).applyStyle(
                        style = Style(
                            size = Size(
                                width = 40.unitReal(),
                                height = 40.unitReal()
                            )
                        )
                    ).applyMargin(left = 12),
                    buildDescriptionIconButton("Cartão de crédito").applyMargin(),
                    buildCreditCardContainer().applyMargin()
                )
            )
        ).toView(this)

    private fun buildHeader() = Container(
        children = listOf(
            Container(
                children = listOf(
                    Container(
                        children = listOf(buildUserAvatar())
                    ).applyStyle(
                        Style(
                            flex = Flex(
                                alignSelf = AlignSelf.FLEX_START
                            )
                        )
                    ),
                    Container(
                        children = listOf(
                            IconTextViewWidget(
                                icon = "\ue81b",
                                iconColor = "#F0F1F5",
                                iconSize = 20.0f
                            ).applyStyle(
                                style = Style(
                                    size = Size(
                                        width = 48.unitReal(),
                                        height = 48.unitReal()
                                    )
                                )
                            ),
                            IconTextViewWidget(
                                icon = "\ue83f",
                                iconColor = "#F0F1F5",
                                iconSize = 20.0f
                            ).applyStyle(
                                style = Style(
                                    size = Size(
                                        width = 48.unitReal(),
                                        height = 48.unitReal()
                                    )
                                )
                            ),
                            IconTextViewWidget(
                                icon = "\ue818",
                                iconColor = "#F0F1F5",
                                iconSize = 20.0f
                            ).applyStyle(
                                style = Style(
                                    size = Size(
                                        width = 48.unitReal(),
                                        height = 48.unitReal()
                                    )
                                )
                            )
                        )
                    ).applyStyle(
                        Style(
                            flex = Flex(
                                flexDirection = FlexDirection.ROW,
                            )
                        )
                    )
                )
            ).applyStyle(
                Style(
                    size = Size(width = 100.unitPercent()),
                    flex = Flex(
                        flexDirection = FlexDirection.ROW,
                        justifyContent = JustifyContent.SPACE_BETWEEN,
                    )
                )
            ),
            Text(
                text = "Olá, Victor",
                textColor = "#ffffff",
                styleId = "BaseText_Medium_Bold"
            ).applyMargin(top = 12, right = 0, left = 0)
        )
    ).applyStyle(
        style = Style(
            padding = EdgeValue(
                horizontal = 24.unitReal(),
                vertical = 24.unitReal()
            ),
            size = Size(
                width = 100.unitPercent()
            ),
            backgroundColor = "#820AD1"
        )
    )

    private fun buildCreditCardContainer() = Container(
        children = listOf(
            Text(
                text = "Fatura atual",
                textColor = "#b2b2b2",
                styleId = "BaseText_Small_Bold"
            ),
            BalanceWidget(
                viewCycleState = expressionOf("@{global.home.onViewStateChange}"),
                context = ContextData("creditWidget", BalanceData(BalanceState.SUCCESS, 1094.80)),
                state = expressionOf("@{creditWidget.state}"),
                balance = expressionOf("@{creditWidget.balance}"),
                errorAction = actionList,
            )
        )
    )

    private fun buildUserAvatar() = Container(
        children = listOf(
            IconTextViewWidget(
                icon = "\ue82a",
                iconColor = "#F0F1F5",
                iconSize = 20.0f
            )
        )
    ).applyStyle(
        Style(
            size = Size(width = 50.unitReal(), height = 50.unitReal()),
            backgroundColor = "#BB86FC",
            cornerRadius = CornerRadius(
                radius = 50.0
            ),
            flex = Flex(
                justifyContent = JustifyContent.CENTER
            )
        )
    )

    private fun buildDescriptionIconButton(title: String) = Container(
        children = listOf(
            Text(
                text = title,
                textColor = "#000000",
                styleId = "BaseText_Medium_Bold"
            ),
            IconTextViewWidget(
                icon = "\ue876",
                iconColor = "#000000",
                iconSize = 16.0f
            ).applyStyle(
                style = Style(
                    size = Size(
                        width = 48.unitReal(),
                        height = 48.unitReal()
                    )
                )
            )
        )
    ).applyStyle(
        Style(
            size = Size(width = 100.unitPercent()),
            flex = Flex(
                flexDirection = FlexDirection.ROW,
                justifyContent = JustifyContent.SPACE_BETWEEN,
                alignItems = AlignItems.CENTER
            )
        )
    )

    private fun buildBalance() = BalanceWidget(
        viewCycleState = expressionOf("@{global.home.onViewStateChange}"),
        onInit = actionList,
        context = ContextData("balanceWidget", BalanceData(BalanceState.LOADING, 0.0)),
        state = expressionOf("@{balanceWidget.state}"),
        balance = expressionOf("@{balanceWidget.balance}"),
        errorAction = actionList,
    )

    private fun buildMenuList(list: List<Widget>) = Container(
        children = listOf(
            ScrollView(
                scrollDirection = ScrollAxis.HORIZONTAL,
                scrollBarEnabled = false,
                children = list.mapIndexed { index, component ->
                    when (index) {
                        0 -> component.applyListHorizontalMargin(24, 8)
                        list.lastIndex -> component.applyListHorizontalMargin(8, 24)
                        else -> component.applyListHorizontalMargin(8, 8)
                    }
                }
            )
        )
    )

    private fun getMenuList() = listOf(
        CircleIconButtonWidget(
            title = "Pix",
            icon = "\ue87b",
            action = listOf()
        ),
        CircleIconButtonWidget(
            title = "Pagar",
            icon = "\ue866",
            action = listOf()
        ),
        CircleIconButtonWidget(
            title = "Transferir",
            icon = "\ue886",
            action = listOf()
        ),
        CircleIconButtonWidget(
            title = "Depositar",
            icon = "\ue867",
            action = listOf()
        ),
        CircleIconButtonWidget(
            title = "Receber",
            icon = "\ue865",
            action = listOf()
        )
    )

    private fun buildBannerList(list: List<Widget>) = Container(
        children = listOf(
            ScrollView(
                scrollDirection = ScrollAxis.HORIZONTAL,
                scrollBarEnabled = false,
                children = list.mapIndexed { index, component ->
                    when (index) {
                        0 -> component.applyListHorizontalMargin(24, 8)
                        list.lastIndex -> component.applyListHorizontalMargin(8, 24)
                        else -> component.applyListHorizontalMargin(8, 8)
                    }
                }
            )
        )
    )

    private fun buildBanner(firstText: String, secondText: String) = Container(
        children = listOf(
            Text(
                text = firstText,
                textColor = "#000000",
                styleId = "BaseText_Small"

            ),
            Text(
                text = secondText,
                textColor = "#000000",
                styleId = "BaseText_Small"
            )
        )
    ).applyStyle(
        Style(
            size = Size(width = 300.unitReal()),
            backgroundColor = "#F0F1F5",
            cornerRadius = CornerRadius(
                radius = 12.0
            ),
            padding = EdgeValue(all = 24.unitReal()),
            flex = Flex(
                flexDirection = FlexDirection.COLUMN,
                alignItems = AlignItems.FLEX_START
            )
        )
    )

    private fun buildBannerButton(icon: String, title: String, info: String) = Container(
        children = listOf(
            IconTextViewWidget(
                icon = icon,
                iconColor = "#000000",
                iconSize = 24.0f
            ).applyStyle(
                style = Style(
                    size = Size(
                        width = 48.unitReal(),
                        height = 48.unitReal()
                    )
                )
            ),
            Text(
                text = title,
                textColor = "#000000",
                styleId = "BaseText_Small_Bold"
            ),
            Text(
                text = info,
                textColor = "#000000",
                styleId = "BaseText_Small_Bold",
                alignment = TextAlignment.CENTER
            ).applyStyle(
                Style(
                    size = Size(
                        width = 48.unitReal(),
                        height = 48.unitReal()
                    )
                )
            )
        )
    ).applyStyle(
        Style(
            size = Size(width = 100.unitPercent()),
            backgroundColor = "#F0F1F5",
            cornerRadius = CornerRadius(
                radius = 12.0
            ),
            padding = EdgeValue(vertical = 8.unitReal()),
            flex = Flex(
                flexDirection = FlexDirection.ROW,
                justifyContent = JustifyContent.SPACE_BETWEEN,
                alignItems = AlignItems.CENTER
            )
        )
    )

    private fun buildDivider(color: String) = Container().applyStyle(
        Style(
            size = Size(width = 100.unitPercent(), height = 2.unitReal()),
            backgroundColor = color,
        )
    )


    private val actionList = listOf(
        SetContext("balanceWidget", BalanceState.LOADING, "state"),
        SendRequest(
            url = valueOf("http://10.0.2.2:8080/balance"),
            onSuccess = listOf(
                SetContext(
                    "balanceWidget",
                    expressionOf<Double>("@{onSuccess.data.balance}"),
                    "balance"
                ),
                SetContext("balanceWidget", BalanceState.SUCCESS, "state")
            ),
            onError = listOf(
                SetContext("balanceWidget", BalanceState.ERROR, "state")
            )
        )

    )

    fun loadLocalComponent() {
        flBalance.addView(
            BalanceWidget(
                viewCycleState = expressionOf("@{global.home.onViewStateChange}"),
                onInit = actionList,
                context = ContextData("balanceWidget", BalanceData(BalanceState.LOADING, 0.0)),
                state = expressionOf("@{balanceWidget.state}"),
                balance = expressionOf("@{balanceWidget.balance}"),
                errorAction = actionList,
            ).applyStyle(
                style = Style(
                    margin = EdgeValue(
                        horizontal = 24.unitReal(),
                        vertical = 24.unitReal()
                    ),
                    flex = Flex(
                        grow = 1.0
                    )
                )
            ).toView(this)
        )
    }

    private fun loadFromServer() {
        flBalance.loadView(this,
            requestData = RequestData(
                url = "http://10.0.2.2:8080/balanceComponent",
                httpAdditionalData = HttpAdditionalData(
                    method = HttpMethod.GET
                )
            ),
            listener = object : OnServerStateChanged {
                override fun invoke(serverState: ServerDrivenState) {
                    when (serverState) {
                        is ServerDrivenState.Started -> {
                            flBalance.removeAllViews()
                        }
                        is ServerDrivenState.Success -> {
                            flBalance.removeAllViews()
                        }

                        is ServerDrivenState.Error -> {
                            flBalance.removeAllViews()
                        }

                        else -> null
                    }
                }

            }
        )
    }
}

private fun Widget.applyMargin(left: Int = 24, top: Int = 0, right: Int = 24, bottom: Int = 0) =
    Container(
        children = listOf(this)
    ).applyStyle(
        Style(
            margin = EdgeValue(
                left = left.unitReal(),
                top = top.unitReal(),
                right = right.unitReal(),
                bottom = bottom.unitReal()
            )
        )
    )

private fun Widget.applyListHorizontalMargin(marginLeft: Int, marginRight: Int) =
    this.apply {
        this.style = (style ?: Style()).copy(
            margin = EdgeValue(
                left = marginLeft.unitReal(),
                right = marginRight.unitReal()
            )
        )

    }
