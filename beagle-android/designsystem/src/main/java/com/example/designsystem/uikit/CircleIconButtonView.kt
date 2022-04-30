package com.example.designsystem.uikit

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.designsystem.R

class CircleIconButtonView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : ConstraintLayout(context, attrs, defStyle) {

    private val title by lazy { findViewById<TextView>(R.id.tv_title) }
    private val circleBackground by lazy { findViewById<FrameLayout>(R.id.fl_circle_background) }
    private val icon by lazy { findViewById<IconTextView>(R.id.itv_balance_icon) }

    init {
        LayoutInflater.from(context).inflate(R.layout.circle_icon_button_view, this, true)
    }

    fun initView(title: String, icon: String, @ColorInt backgroundColor: Int? = null) {

        this.title.text = title
        this.icon.text = icon

        backgroundColor?.let { color ->
            circleBackground.background.setTint(color)
        }
    }

}