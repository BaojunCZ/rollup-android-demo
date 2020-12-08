package demo.rollup.utils

import org.web3j.protocol.Web3j
import org.web3j.protocol.core.DefaultBlockParameterName
import org.web3j.protocol.http.HttpService
import java.math.BigInteger

class ArbitrumManager(l1Rpc: String, l2Rpc: String) {
    private val l1Web3j by lazy {
        Web3j.build(HttpService(l1Rpc))
    }
    private val l2Web3j by lazy {
        Web3j.build(HttpService(l2Rpc))
    }

    @Throws
    fun getL1Balance(address: String): BigInteger {
        return l1Web3j.ethGetBalance(address, DefaultBlockParameterName.LATEST).send().balance
    }

}