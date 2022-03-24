package com.example.serverdriven

import br.com.zup.beagle.android.annotation.BeagleComponent
import br.com.zup.beagle.android.setup.DesignSystem

@BeagleComponent
class CustomDesignSystem : DesignSystem() {

    override fun textStyle(id: String): Int? = when (id) {
        "BaseText_Small" -> R.style.BaseText_Small
        "BaseText_Medium" -> R.style.BaseText_Medium
        "BaseText_Large" -> R.style.BaseText_Large
        "BaseText_Small_Bold" -> R.style.BaseText_Small_Bold
        "BaseText_Medium_Bold" -> R.style.BaseText_Medium_Bold
        "BaseText_Large_Bold" -> R.style.BaseText_Large_Bold
        else -> super.textStyle(id)
    }

}