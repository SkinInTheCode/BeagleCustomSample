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
    private val toggleBalanceButton by lazy { findViewById<IconTextView>(R.id.itv_toggle_balance_icon) }
    private val toggleBalanceView by lazy { findViewById<View>(R.id.v_balance_toggle_view) }

    private var balanceToggleListener: BalanceToggleListener? = null

    var balanceVisible = true
        private set

    init {
        LayoutInflater.from(context).inflate(R.layout.balance_view, this, true)
        toggleBalanceButton.setOnClickListener { toggleBalance() }
    }

    fun setBalance(balance: Double) {
        tvBalance.text = NumberFormat.getCurrencyInstance(Locale("pt", "BR")).format(balance)
    }

    fun showBalance() {
        hideView()
        toggleBalanceButton.visibility = View.VISIBLE

        if (balanceVisible)
            tvBalance.visibility = View.VISIBLE
        else
            toggleBalanceView.visibility = View.VISIBLE
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

    fun toggleHideBalance() {
        balanceVisible = false
        toggleBalanceButton.text = IC_TOGGLE_VISIBLE
        tvBalance.visibility = View.GONE
        toggleBalanceView.visibility = View.VISIBLE
    }

    fun toggleShowBalance() {
        balanceVisible = true
        toggleBalanceButton.text = IC_TOGGLE_INVISIBLE
        toggleBalanceView.visibility = View.GONE
        tvBalance.visibility = View.VISIBLE
    }

    fun setBalanceToggleListener(listener: BalanceToggleListener) {
        this.balanceToggleListener = listener
    }

    private fun hideView() {
        tvBalance.visibility = View.GONE
        ivError.visibility = View.GONE
        shimmer.visibility = View.GONE
        toggleBalanceButton.visibility = View.GONE
        toggleBalanceView.visibility = View.GONE
    }

    private fun toggleBalance() {
        if (balanceVisible)
            toggleHideBalance()
        else
            toggleShowBalance()

        balanceToggleListener?.toggleOnChange(balanceVisible)
    }

    companion object {
        private const val IC_TOGGLE_VISIBLE = "\uE875"
        private const val IC_TOGGLE_INVISIBLE = "\uE876"
    }
}

interface BalanceToggleListener {
    fun toggleOnChange(isVisible: Boolean)
}