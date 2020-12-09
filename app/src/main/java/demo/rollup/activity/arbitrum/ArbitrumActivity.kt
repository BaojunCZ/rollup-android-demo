package demo.rollup.activity.arbitrum

import android.annotation.SuppressLint
import android.view.MenuItem
import android.widget.Toast
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
            it?.let {
                binding.tvL1Balance.text = EthUtils.formatToEth(it).toString() + "eth"
            } ?: {
                Toast.makeText(this, "查询Layer 1余额失败", Toast.LENGTH_SHORT).show()
            }.invoke()
        }
        viewModel.l2Balance.observe(this) {
            it?.let {
                binding.tvL2Balance.text = EthUtils.formatToEth(it).toString() + "eth"
            } ?: {
                Toast.makeText(this, "查询Layer 2余额失败", Toast.LENGTH_SHORT).show()
            }.invoke()
        }
        viewModel.l1Inbox.observe(this) {
            it?.let {
                binding.tvL1LockBoxBalance.text = EthUtils.formatToEth(it).toString() + "eth"
            } ?: {
                Toast.makeText(this, "查询Inbox余额失败", Toast.LENGTH_SHORT).show()
            }.invoke()
        }
        viewModel.l1Pending.observe(this) {
            it?.let {
                binding.tvL1Pending.text = EthUtils.formatToEth(it).toString() + "eth"
            } ?: {
                Toast.makeText(this, "查询Pending余额失败", Toast.LENGTH_SHORT).show()
            }.invoke()
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