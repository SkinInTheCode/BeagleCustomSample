package com.example.serverdriven

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import br.com.zup.beagle.android.action.SetContext
import br.com.zup.beagle.android.data.serializer.BeagleJsonSerializerFactory
import br.com.zup.beagle.android.utils.loadView
import br.com.zup.beagle.android.widget.RootView
import br.com.zup.beagle.android.widget.core.ServerDrivenComponent
import java.lang.Exception

fun ViewGroup.loadView(activity: AppCompatActivity, component: ServerDrivenComponent) {
    loadView(
        activity = activity,
        screenJson = BeagleJsonSerializerFactory.serializer.serializeComponent(component)
    )
}

fun ViewGroup.loadView(fragment: Fragment, component: ServerDrivenComponent) {
    loadView(
        fragment = fragment,
        screenJson = BeagleJsonSerializerFactory.serializer.serializeComponent(component)
    )
}

fun ViewGroup.loadView(context: Context, component: ServerDrivenComponent) {
    when (context) {
        is AppCompatActivity -> loadView(
            context,
            screenJson = BeagleJsonSerializerFactory.serializer.serializeComponent(component)
        )
        is Fragment -> loadView(
            context,
            screenJson = BeagleJsonSerializerFactory.serializer.serializeComponent(component)
        )
        else -> Exception()
    }
}

fun ServerDrivenComponent.toView(activity: AppCompatActivity, component: ServerDrivenComponent) =
    FrameLayout(activity).apply {
        loadView(
            activity = activity,
            screenJson = BeagleJsonSerializerFactory.serializer.serializeComponent(component)
        )
    }

fun ServerDrivenComponent.toView(fragment: Fragment, component: ServerDrivenComponent) =
    FrameLayout(fragment.requireContext()).apply {
        loadView(
            fragment = fragment,
            screenJson = BeagleJsonSerializerFactory.serializer.serializeComponent(component)
        )
    }

