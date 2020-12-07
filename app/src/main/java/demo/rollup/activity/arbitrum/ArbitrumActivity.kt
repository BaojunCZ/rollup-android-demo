package demo.rollup.activity.arbitrum

import android.view.MenuItem
import demo.rollup.R
import demo.rollup.bsae.BaseActivity
import demo.rollup.databinding.ActivityArbitrumBinding

class ArbitrumActivity : BaseActivity<ActivityArbitrumBinding>() {

    override val contentLayout: Int
        get() = R.layout.activity_arbitrum

    override fun initData() {
        supportActionBar?.title = "ARBITRUM"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
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