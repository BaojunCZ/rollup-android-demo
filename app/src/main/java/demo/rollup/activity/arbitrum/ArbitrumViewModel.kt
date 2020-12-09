package demo.rollup.activity.arbitrum

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import demo.rollup.constant.Constants
import demo.rollup.manager.arbitrum.ArbitrumConstants
import demo.rollup.manager.arbitrum.ArbitrumManager
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
    val l2Balance = MutableLiveData(BigInteger.ZERO)
    val l1Inbox = MutableLiveData(BigInteger.ZERO)
    val l1Pending = MutableLiveData(BigInteger.ZERO)

    init {
        getL1Balance()
        getL2Balance()
        getL1InboxBalance()
    }

    private fun getL1Balance() {
        viewModelScope.launch(IO) {
            try {
                l1Balance.postValue(arbitrumManager.getL1Balance(address))
            } catch (e: Exception) {
                e.printStackTrace()
                l1Balance.postValue(null)
            }
        }
    }

    private fun getL2Balance() {
        viewModelScope.launch(IO) {
            try {
                l2Balance.postValue(arbitrumManager.getL2Balance(address))
            } catch (e: Exception) {
                e.printStackTrace()
                l2Balance.postValue(null)
            }
        }
    }

    private fun getL1InboxBalance() {
        viewModelScope.launch(IO) {
            try {
                l1Inbox.postValue(arbitrumManager.getL1InboxBalance(address))
            } catch (e: java.lang.Exception) {
                e.printStackTrace()
                l1Inbox.postValue(null)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}