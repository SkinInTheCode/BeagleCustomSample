package com.example.designsystem.uikit

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import android.view.Gravity

class IconTextView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : androidx.appcompat.widget.AppCompatTextView(context, attrs, defStyle) {

    init {
        configIconFont()
        text
    }

    private fun configIconFont() {
        gravity = Gravity.CENTER
        setTypeface(getIconFont(context), Typeface.BOLD)
    }

    private companion object {

        private lateinit var typeface: Typeface

        fun getIconFont(context: Context) = if (::typeface.isInitialized) typeface else {
            typeface = Typeface.createFromAsset(context.assets, "fonts/IcoMoon.ttf")
            typeface
        }
    }

}