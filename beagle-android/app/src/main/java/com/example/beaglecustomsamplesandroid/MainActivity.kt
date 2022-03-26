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
        setContentView(R.layout.activity_main)
        loadFromServer()
    }

    override fun onResume() {
        super.onResume()
        onViewStateChange("home", ViewCycleListener.ViewState.RESUME)
    }

    override fun onPause() {
        super.onPause()
        onViewStateChange("home", ViewCycleListener.ViewState.PAUSE)
    }

    private fun loadFromServer() {
        flBalance.loadView(this,
            requestData = RequestData(
                url = "http://10.0.2.2:8080/home/screen",
                httpAdditionalData = HttpAdditionalData(
                    method = HttpMethod.GET
                )
            ),
            listener = object : OnServerStateChanged {
                override fun invoke(serverState: ServerDrivenState) {
                    when (serverState) {
                        is ServerDrivenState.Started -> {

                        }
                        is ServerDrivenState.Success -> {
                            flBalance.removeAllViews()
                        }

                        is ServerDrivenState.Error -> {
                            flBalance.removeAllViews()
                        }

                        else -> {}
                    }
                }
            }
        )
    }
}

