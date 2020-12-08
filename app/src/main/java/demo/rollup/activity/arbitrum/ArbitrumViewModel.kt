package demo.rollup.activity.arbitrum

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import demo.rollup.constant.ArbitrumConstants
import demo.rollup.constant.Constants
import demo.rollup.utils.ArbitrumManager
import demo.rollup.utils.SPUtil
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import org.web3j.crypto.ECKeyPair
import org.web3j.crypto.Keys
import org.web3j.utils.Numeric
import java.math.BigInteger

class ArbitrumViewModel : ViewModel() {

    private val arbitrumManager by lazy {
        ArbitrumManager(ArbitrumConstants.L1Rpc, ArbitrumConstants.L2Rpc)
    }
    private val ecKeyPair = ECKeyPair.create(
        Numeric.hexStringToByteArray(
            SPUtil<String>(Constants.SP_PRIVATE_KEY).get("")
        )
    )
    private val address: String = Numeric.prependHexPrefix(Keys.getAddress(ecKeyPair))

    val l1Balance = MutableLiveData(BigInteger.ZERO)

    init {
        getL1Balance()
    }

    fun getL1Balance() {
        viewModelScope.launch(IO) {
            try {
                val balance = arbitrumManager.getL1Balance(address)
                l1Balance.postValue(balance)
            } catch (e: Exception) {
                e.printStackTrace()
                l1Balance.postValue(BigInteger.ZERO)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}