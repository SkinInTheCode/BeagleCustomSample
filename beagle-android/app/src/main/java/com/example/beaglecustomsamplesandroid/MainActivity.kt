package com.example.beaglecustomsamplesandroid

import android.os.Bundle
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import br.com.zup.beagle.android.action.Action
import br.com.zup.beagle.android.action.SendRequest
import br.com.zup.beagle.android.action.SetContext
import br.com.zup.beagle.android.components.layout.Container
import br.com.zup.beagle.android.components.layout.Screen
import br.com.zup.beagle.android.context.ContextData
import br.com.zup.beagle.android.context.expressionOf
import br.com.zup.beagle.android.context.valueOf
import br.com.zup.beagle.android.utils.loadView
import br.com.zup.beagle.android.utils.toView
import br.com.zup.beagle.android.view.ScreenMethod
import br.com.zup.beagle.android.view.ScreenRequest
import br.com.zup.beagle.android.view.ServerDrivenState
import br.com.zup.beagle.android.view.custom.OnServerStateChanged
import br.com.zup.beagle.core.Style
import br.com.zup.beagle.ext.applyStyle
import br.com.zup.beagle.ext.unitReal
import br.com.zup.beagle.widget.core.EdgeValue
import br.com.zup.beagle.widget.core.Size
import com.example.serverdriven.ViewCycleListener
import com.example.serverdriven.components.balance.widgets.BalanceState
import com.example.serverdriven.components.balance.widgets.BalanceWidget
import com.example.serverdriven.onViewStateChange

class MainActivity : AppCompatActivity() {

    private val flBalance by lazy { findViewById<FrameLayout>(R.id.flBalance) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadLocalComponent()
    }

    override fun onResume() {
        super.onResume()
        //loadFromServer()
        onViewStateChange("home", ViewCycleListener.ViewState.RESUME)
    }

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

    data class BalanceData(val state : BalanceState, val balance : Double)

    fun loadLocalComponent(){

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
                    size = Size(200.unitReal(), 64.unitReal())
                )
            ).toView(this)
        )
    }

    private fun loadFromServer() {
        flBalance.loadView(this,
            screenRequest = ScreenRequest(
                "http://10.0.2.2:8080/balanceComponent",
                method = ScreenMethod.GET
            ),
            listener = object : OnServerStateChanged {
                override fun invoke(serverState: ServerDrivenState) {
                    when (serverState) {
                        is ServerDrivenState.Started -> {
                            flBalance.removeAllViews()
                            //flBalance.addView(BalanceWidget(state = valueOf(BalanceState.LOADING)).toView(this@MainActivity))
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


    fun teste(){

        var actions = listOf<Action>()
        var data = ContainerData(actions)

        Container(
            onInit = actions,
            context = ContextData ("action", data),
            children = listOf()
        )

    }

}

data class ContainerData( val onInit: List<Action> )