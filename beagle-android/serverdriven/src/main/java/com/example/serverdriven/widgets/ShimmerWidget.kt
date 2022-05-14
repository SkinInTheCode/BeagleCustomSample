package com.example.serverdriven.widgets

import br.com.zup.beagle.android.annotation.RegisterWidget
import br.com.zup.beagle.android.widget.RootView
import br.com.zup.beagle.android.widget.WidgetView
import br.com.zup.beagle.android.widget.core.ServerDrivenComponent
import com.example.serverdriven.loadView
import com.facebook.shimmer.ShimmerFrameLayout

@RegisterWidget
class ShimmerWidget(
    private val child: ServerDrivenComponent
) : WidgetView() {

    override fun buildView(rootView: RootView) = ShimmerFrameLayout(rootView.getContext()).apply {
        loadView(rootView.getContext(), child)
        startShimmer()
    }
}