package com.example.serverdriven.widgets

import android.graphics.Color
import br.com.zup.beagle.android.action.Action
import br.com.zup.beagle.android.widget.RootView
import br.com.zup.beagle.android.widget.WidgetView
import br.com.zup.beagle.annotation.RegisterWidget
import com.example.designsystem.uikit.CircleIconButtonView

@RegisterWidget
class CircleIconButtonWidget(
    private val title: String,
    private val icon: String,
    private val backgroundColor: String? = null,
    private val action: List<Action>
) : WidgetView() {

    override fun buildView(rootView: RootView) = CircleIconButtonView(rootView.getContext()).apply {

        val color = if (backgroundColor != null) {
            Color.parseColor(backgroundColor)
        } else null

        initView(
            title = title,
            icon = icon,
            backgroundColor = color
        )

        setOnClickListener {
            action.forEach {
                it.execute(rootView, this)
            }
        }
    }
}