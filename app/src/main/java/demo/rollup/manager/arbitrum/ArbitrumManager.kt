package demo.rollup.manager.arbitrum

import demo.rollup.manager.arbitrum.ArbitrumConstants.GlobalInboxContract
import demo.rollup.manager.arbitrum.abi.GlobalInboxRepository
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
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
    private val globalInboxRepository by lazy {
        GlobalInboxRepository(l1Web3j)
    }


    @Throws
    suspend fun getL1Balance(address: String): BigInteger? {
        return withContext(IO) {
            l1Web3j.ethGetBalance(address, DefaultBlockParameterName.LATEST).send().balance
        }
    }

    @Throws
    suspend fun getL2Balance(address: String): BigInteger? {
        return withContext(IO) {
            l2Web3j.ethGetBalance(address, DefaultBlockParameterName.LATEST).send().balance
        }
    }

    @Throws
    suspend fun getL1InboxBalance(address: String): BigInteger? {
        return globalInboxRepository.getEthBalance(address, GlobalInboxContract)
    }

}