package com.example.designsystem.uikit

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.designsystem.R
import com.facebook.shimmer.ShimmerFrameLayout
import java.text.NumberFormat
import java.util.*

class BalanceView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : ConstraintLayout(context, attrs, defStyle) {

    private val tvBalance by lazy { findViewById<TextView>(R.id.tvBalance) }
    private val shimmer by lazy { findViewById<ShimmerFrameLayout>(R.id.shimmer_view_container) }
    private val ivError by lazy { findViewById<IconTextView>(R.id.itv_error_icon) }

    init {
        LayoutInflater.from(context).inflate(R.layout.balance_view, this, true)
    }

    fun setBalance(balance: Double){
        tvBalance.text = NumberFormat.getCurrencyInstance(Locale("pt", "BR")).format(balance)
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
        tvBalance.text = "error ao carregar"
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