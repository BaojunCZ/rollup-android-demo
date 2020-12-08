package demo.rollup.activity.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import demo.rollup.constant.Constants
import demo.rollup.utils.SPUtil
import kotlinx.coroutines.cancel
import org.web3j.crypto.ECKeyPair
import org.web3j.crypto.Keys
import org.web3j.utils.Numeric

class MainViewModel : ViewModel() {

    var hasWallet = MutableLiveData(false)
    var address = MutableLiveData("")

    init {
        val privateKey = SPUtil<String>(Constants.SP_PRIVATE_KEY).get("")
        if (privateKey.isBlank()) {
            hasWallet.postValue(false)
        } else {
            hasWallet.postValue(true)
            address.postValue(
                Keys.getAddress(
                    ECKeyPair.create(
                        Numeric.hexStringToByteArray(
                            privateKey
                        )
                    )
                )
            )
        }
    }

    fun getAddress() {

    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}