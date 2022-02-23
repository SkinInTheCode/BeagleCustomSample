package br.com.sknc.beagle.bff.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import br.com.sknc.beagle.bff.service.ItiService
import br.com.sknc.beagle.bff.ui.widgets.BalanceData
import br.com.sknc.beagle.bff.ui.widgets.BalanceState
import br.com.sknc.beagle.bff.ui.widgets.BalanceWidget
import br.com.zup.beagle.action.sendRequest
import br.com.zup.beagle.core.Style
import br.com.zup.beagle.ext.applyStyle
import br.com.zup.beagle.ext.unitReal
import br.com.zup.beagle.widget.action.Action
import br.com.zup.beagle.widget.action.SendRequest
import br.com.zup.beagle.widget.action.SetContext
import br.com.zup.beagle.widget.context.Bind
import br.com.zup.beagle.widget.context.ContextData
import br.com.zup.beagle.widget.context.expressionOf
import br.com.zup.beagle.widget.context.valueOf
import br.com.zup.beagle.widget.core.EdgeValue
import br.com.zup.beagle.widget.core.Flex
import br.com.zup.beagle.widget.core.Size
import br.com.zup.beagle.widget.ui.Button

@RestController()
class ItiController(private val itiService: ItiService) {

    @GetMapping("home/iti")
    fun getScreenIti() = itiService.getScreenIti()

    @GetMapping("home/iti/banners")
    fun getBannerScreen() = itiService.getBannerCarouselScreen()

    @GetMapping("home/iti/banners/customwidget")
    fun getCustomBannerScreen() = itiService.getBannerCarouselScreen()


    @GetMapping("balance")
    fun getBalance() =  BalanceWidget(
            onInit = actionList,
            context = ContextData("balanceWidget", BalanceData(BalanceState.LOADING, 0.0)),
            state = expressionOf("@{balanceWidget.state}"),
            balance = expressionOf("@{balanceWidget.balance}"),
            errorAction = actionList,
            viewCycleState = valueOf("")
    ).applyStyle(
            style = Style(
                    margin = EdgeValue(
                            horizontal = 24.unitReal(),
                            vertical = 24.unitReal()
                    ),
                    size = Size(200.unitReal(), 64.unitReal())
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



}