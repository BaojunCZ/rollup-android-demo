package demo.rollup.activity

import android.content.Intent
import demo.rollup.R
import demo.rollup.activity.arbitrum.ArbitrumActivity
import demo.rollup.bsae.BaseActivity
import demo.rollup.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>() {

    override val contentLayout: Int
        get() = R.layout.activity_main

    override fun initAction() {
        binding.btnArbitrum.setOnClickListener {
            startActivity(Intent(this, ArbitrumActivity::class.java))
        }
    }
}