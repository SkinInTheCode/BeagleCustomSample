package com.example.designsystem.uikit.balance

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.View.inflate
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.view.allViews
import androidx.core.view.forEach
import com.example.designsystem.R
import com.facebook.shimmer.ShimmerFrameLayout
import java.text.NumberFormat
import java.util.*

class CustomBalanceView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : ConstraintLayout(context, attrs, defStyle) {

    private val tvTitle by lazy { findViewById<TextView>(R.id.tvTitle) }
    private val tvBalance by lazy { findViewById<TextView>(R.id.tvBalance) }
    private val shimmer by lazy { findViewById<ShimmerFrameLayout>(R.id.shimmer_view_container) }
    private val ivError by lazy { findViewById<ImageView>(R.id.imageView) }

    init {
        LayoutInflater.from(context).inflate(R.layout.custom_balance_view, this, true)
    }

    fun setBalance(balance: Double){
        tvBalance.text = NumberFormat.getCurrencyInstance(Locale.getDefault()).format(balance)
    }

    fun showBalance() {
        hideView()
        tvBalance.visibility = View.VISIBLE
    }

    fun showLoading(isLoading: Boolean) {
        hideView()
        shimmer.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    fun showError(action: () -> Unit) {
        hideView()
        tvBalance.text = "Error ao carregar"
        ivError.setOnClickListener { action() }

        tvBalance.visibility = View.VISIBLE
        ivError.visibility = View.VISIBLE
    }

    private fun hideView() {
        tvBalance.visibility = View.GONE
        ivError.visibility = View.GONE
        shimmer.visibility = View.GONE
    }

}