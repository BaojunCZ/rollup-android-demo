package demo.rollup.activity.main

import android.app.AlertDialog
import android.content.Intent
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import demo.rollup.R
import demo.rollup.activity.arbitrum.ArbitrumActivity
import demo.rollup.bsae.BaseActivity
import demo.rollup.constant.Constants
import demo.rollup.databinding.ActivityMainBinding
import demo.rollup.utils.SPUtil
import org.web3j.crypto.ECKeyPair
import org.web3j.crypto.Keys
import org.web3j.utils.Numeric

class MainActivity : BaseActivity<ActivityMainBinding>() {

    private val viewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }

    override val contentLayout: Int
        get() = R.layout.activity_main

    override fun initData() {
        binding.viewModel = viewModel
    }

    override fun initObserver() {
        viewModel.address.observe(this) {
            binding.tvAddress.text = it
        }
    }

    override fun initAction() {
        binding.btnArbitrum.setOnClickListener {
            startActivity(Intent(this, ArbitrumActivity::class.java))
        }
        binding.btnPrivateKey.setOnClickListener {
            inputPrivateKey()
        }
    }

    private fun inputPrivateKey() {
        val et = EditText(this)
        AlertDialog.Builder(this)
            .setTitle("输入私钥")
            .setView(et)
            .setPositiveButton("确定") { dialog, _ ->
                val input = et.text.toString().trim()
                if (input.isNotBlank()) {
                    dialog.dismiss()
                    try {
                        viewModel.address.postValue(
                            Keys.getAddress(
                                ECKeyPair.create(
                                    Numeric.hexStringToByteArray(
                                        input
                                    )
                                )
                            )
                        )
                        viewModel.hasWallet.postValue(true)
                        SPUtil<String>(Constants.SP_PRIVATE_KEY).put(input)
                    } catch (e: Exception) {
                        Toast.makeText(this, "私钥错误", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this, "请输入私钥", Toast.LENGTH_SHORT).show()
                }
            }
            .setNegativeButton("取消", null)
            .show()
    }
}