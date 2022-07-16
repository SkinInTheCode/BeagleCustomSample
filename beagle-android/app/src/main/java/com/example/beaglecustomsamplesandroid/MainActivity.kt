package com.example.beaglecustomsamplesandroid

import android.os.Bundle
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import br.com.zup.beagle.android.action.Alert
import br.com.zup.beagle.android.action.SendRequest
import br.com.zup.beagle.android.action.SetContext
import br.com.zup.beagle.android.components.Button
import br.com.zup.beagle.android.components.Text
import br.com.zup.beagle.android.components.TextInput
import br.com.zup.beagle.android.components.layout.Container
import br.com.zup.beagle.android.context.ContextData
import br.com.zup.beagle.android.context.constant
import br.com.zup.beagle.android.context.expressionOf
import br.com.zup.beagle.android.networking.HttpAdditionalData
import br.com.zup.beagle.android.networking.HttpMethod
import br.com.zup.beagle.android.networking.RequestData
import br.com.zup.beagle.android.utils.loadView
import br.com.zup.beagle.android.view.ServerDrivenState
import br.com.zup.beagle.android.view.custom.OnServerStateChanged
import br.com.zup.beagle.android.widget.core.*
import com.example.serverdriven.actions.RetrieveValueLocalAction
import com.example.serverdriven.actions.SaveValueLocalAction
import com.example.serverdriven.loadView
import com.example.serverdriven.widgets.observer.ServerDrivenLifeCycleObserver
import java.io.BufferedReader

class MainActivity : AppCompatActivity() {

    private val flBalance by lazy { findViewById<FrameLayout>(R.id.flBalance) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadFromServer()

        //flBalance.loadView(this, buildRetrieveActionTest().wrapperLifeCycleObserve())
    }

    data class ContextTest(
        val text: String = "Valor Inicial", val dynamicText: String = "",
        val value: String = ""
    )

    private val contextId = "MainActivity"
    private val storageKey = "MainActivityStorage"


    private fun ServerDrivenComponent.wrapperLifeCycleObserve() = ServerDrivenLifeCycleObserver(
        context = ContextData(contextId, ContextTest()),
        child = this,
    )

    private fun buildRetrieveActionTest(): ServerDrivenComponent {
        return Container(
            onInit = listOf(
                RetrieveValueLocalAction(
                    key = storageKey,
                    onValue = listOf(
                        SetContext(
                            contextId,
                            "@{onValue}",
                            "text"
                        )
                    )
                )
            ),
            children = listOf(
                Text(
                    text = expressionOf("@{$contextId.text}"),
                    textColor = constant("#000")
                ).apply {
                    style = Style(
                        margin = EdgeValue(all = UnitValue(constant(24.0), UnitType.REAL))
                    )
                },
                Button(
                    text = constant("Buscar valor salvo"),
                    onPress = listOf(
                        RetrieveValueLocalAction(
                            key = storageKey,
                            onValue = listOf(
                                SetContext(
                                    contextId,
                                    "@{onValue}",
                                    "text"
                                )
                            )
                        )
                    )
                ).apply {
                    style = Style(
                        margin = EdgeValue(all = UnitValue(constant(24.0), UnitType.REAL))
                    )
                },
                TextInput(
                    type = constant(TextInputType.TEXT),
                    onChange = listOf(
                        SetContext(
                            contextId,
                            "@{onChange.value}",
                            "dynamicText"
                        )
                    )
                ).apply {
                    style = Style(
                        margin = EdgeValue(all = UnitValue(constant(24.0), UnitType.REAL))
                    )
                },
                Button(
                    text = constant("Salvar valor digitado"),
                    onPress = listOf(
                        SaveValueLocalAction(
                            key = storageKey,
                            value = expressionOf("@{$contextId.dynamicText}")
                        )
                    )
                ).apply {
                    style = Style(
                        margin = EdgeValue(all = UnitValue(constant(24.0), UnitType.REAL))
                    )
                }
            )

        )

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

    private fun readFromAsset(): String {
        val file_name = "android_version.json"
        val bufferReader = application.assets.open(file_name).bufferedReader()
        val data = bufferReader.use { it.readText() }

        return data
    }
}

