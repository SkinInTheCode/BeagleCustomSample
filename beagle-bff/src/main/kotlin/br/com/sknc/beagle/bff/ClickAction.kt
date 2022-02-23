package br.com.sknc.beagle.bff

import br.com.zup.beagle.annotation.RegisterAction
import br.com.zup.beagle.annotation.RegisterWidget
import br.com.zup.beagle.widget.Widget
import br.com.zup.beagle.widget.action.Action

@RegisterAction
class CustomAction(val texto: String): Action

@RegisterWidget
class ButtonWidget(val actions: List<Action>, val title: String ) : Widget()
