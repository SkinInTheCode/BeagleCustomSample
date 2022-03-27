package com.example.serverdriven.widgets

import androidx.appcompat.app.AppCompatActivity
import br.com.zup.beagle.android.utils.toView
import br.com.zup.beagle.android.widget.RootView
import br.com.zup.beagle.android.widget.WidgetView
import br.com.zup.beagle.annotation.RegisterWidget
import br.com.zup.beagle.core.ServerDrivenComponent
import br.com.zup.beagle.core.SingleChildComponent
import com.facebook.shimmer.ShimmerFrameLayout

@RegisterWidget
class ShimmerWidget(
    override val child: ServerDrivenComponent
) : WidgetView(), SingleChildComponent {

    override fun buildView(rootView: RootView) = ShimmerFrameLayout(rootView.getContext()).apply {
        addView(child.toView(rootView.getContext() as AppCompatActivity))
        startShimmer()
    }

}