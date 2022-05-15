package com.example.beaglecustomsamplesandroid

import android.os.Bundle
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import br.com.zup.beagle.android.networking.HttpAdditionalData
import br.com.zup.beagle.android.networking.HttpMethod
import br.com.zup.beagle.android.networking.RequestData
import br.com.zup.beagle.android.utils.loadView
import br.com.zup.beagle.android.view.ServerDrivenState
import br.com.zup.beagle.android.view.custom.OnServerStateChanged

class MainActivity : AppCompatActivity() {

    private val flBalance by lazy { findViewById<FrameLayout>(R.id.flBalance) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadFromServer()
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

