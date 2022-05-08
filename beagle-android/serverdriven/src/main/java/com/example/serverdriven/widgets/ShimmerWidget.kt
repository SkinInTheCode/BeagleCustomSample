package com.example.serverdriven.widgets

import androidx.appcompat.app.AppCompatActivity
import br.com.zup.beagle.android.annotation.RegisterWidget
import br.com.zup.beagle.android.utils.loadView
import br.com.zup.beagle.android.widget.RootView
import br.com.zup.beagle.android.widget.WidgetView
import com.facebook.shimmer.ShimmerFrameLayout
import org.json.JSONObject


@RegisterWidget
class ShimmerWidget(
    private val child: JSONObject
) : WidgetView() {

    override fun buildView(rootView: RootView) = ShimmerFrameLayout(rootView.getContext()).apply {
        this.loadView( (rootView.getContext() as AppCompatActivity), child.toString() )
        startShimmer()
    }
}