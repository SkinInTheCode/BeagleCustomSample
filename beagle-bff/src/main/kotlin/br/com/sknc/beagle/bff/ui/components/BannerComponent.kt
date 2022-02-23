package br.com.sknc.beagle.bff.ui.components

import br.com.zup.beagle.core.*
import br.com.zup.beagle.ext.applyStyle
import br.com.zup.beagle.ext.unitReal
import br.com.zup.beagle.widget.core.*
import br.com.zup.beagle.widget.layout.ComposeComponent
import br.com.zup.beagle.widget.navigation.Touchable
import br.com.sknc.beagle.bff.data.models.CrmData
import br.com.zup.beagle.widget.ui.ImagePath


class BannerComponent(private val data: CrmData) : ComposeComponent {

    override fun build(): ServerDrivenComponent {
        return Touchable(
                onPress = data.listAction,
                child = br.com.zup.beagle.widget.ui.Image(
                        mode = ImageContentMode.CENTER_CROP,
                        path = ImagePath.Remote(remoteUrl = data.urlBackground)).applyStyle(
                        Style(
                                margin = EdgeValue(10.unitReal(), 10.unitReal(), 10.unitReal(), 10.unitReal()),
                                size = Size(height = 130.unitReal()),
                                positionType = PositionType.RELATIVE,
                                cornerRadius = CornerRadius(30.0)
                        )
                )
        )
    }

}