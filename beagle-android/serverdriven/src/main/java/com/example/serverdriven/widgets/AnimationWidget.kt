package com.example.serverdriven.widgets

import android.content.Context
import br.com.zup.beagle.android.annotation.RegisterWidget
import br.com.zup.beagle.android.widget.RootView
import br.com.zup.beagle.android.widget.WidgetView
import com.airbnb.lottie.LottieAnimationView

@RegisterWidget
class AnimationWidget(
    private val type : Type,
    private val value: String,
    private val repeatCount : Int
) : WidgetView(){

    enum class Type {
        JSON,
        TOKEN
    }

    override fun buildView(rootView: RootView) = LottieAnimationView(rootView.getContext()).also {
        it.repeatCount = repeatCount
        when(type){
            Type.JSON -> { it.setAnimationFromJson(value, null) }
            Type.TOKEN -> { }
        }

    }.also {
        it.playAnimation()
    }

}

fun Context.findAnimationByName(name: String){
    resources.getIdentifier(name, "assets", packageName)
}