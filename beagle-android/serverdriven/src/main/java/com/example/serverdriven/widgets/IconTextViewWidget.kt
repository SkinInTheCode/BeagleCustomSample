package com.example.serverdriven.widgets

import android.graphics.Color
import android.util.TypedValue
import br.com.zup.beagle.android.widget.RootView
import br.com.zup.beagle.android.widget.WidgetView
import br.com.zup.beagle.annotation.RegisterWidget
import com.example.designsystem.uikit.IconTextView

@RegisterWidget
class IconTextViewWidget(
    private val icon: String,
    private val iconColor: String? = null,
    private val iconSize: Float? = null
) : WidgetView() {

    override fun buildView(rootView: RootView) = IconTextView(rootView.getContext()).apply {
        text = icon
        iconColor?.let { setTextColor(Color.parseColor(it)) }
        iconSize?.let { setTextSize(TypedValue.COMPLEX_UNIT_SP, it) }
    }

}