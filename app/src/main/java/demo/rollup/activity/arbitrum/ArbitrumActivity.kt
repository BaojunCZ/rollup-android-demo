package demo.rollup.activity.arbitrum

import android.annotation.SuppressLint
import android.util.Log
import android.view.MenuItem
import androidx.lifecycle.ViewModelProvider
import demo.rollup.R
import demo.rollup.bsae.BaseActivity
import demo.rollup.databinding.ActivityArbitrumBinding
import demo.rollup.utils.EthUtils

class ArbitrumActivity : BaseActivity<ActivityArbitrumBinding>() {

    private val viewModel by lazy {
        ViewModelProvider(this)[ArbitrumViewModel::class.java]
    }

    override val contentLayout: Int
        get() = R.layout.activity_arbitrum

    override fun initData() {
        supportActionBar?.title = "ARBITRUM"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.viewModel = viewModel
    }

    @SuppressLint("SetTextI18n")
    override fun initObserver() {
        viewModel.l1Balance.observe(this) {
            Log.e("balance", it.toString(16))
            binding.tvL1Balance.text = EthUtils.formatToEth(it).toString() + "eth"
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> {
                false
            }
        }
    }
}