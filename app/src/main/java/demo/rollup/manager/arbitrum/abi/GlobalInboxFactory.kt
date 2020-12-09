package demo.rollup.manager.arbitrum.abi

import demo.rollup.utils.EthUtils
import org.web3j.abi.TypeReference
import org.web3j.abi.datatypes.Address
import org.web3j.abi.datatypes.Function
import org.web3j.abi.datatypes.generated.Int256
import org.web3j.protocol.Web3j
import java.math.BigInteger

class GlobalInboxRepository(private val web3j: Web3j) {

    companion object {
        private const val GET_ERC20_BALANCE = "getEthBalance"
    }

    suspend fun getEthBalance(address: String, contractAddress: String): BigInteger? {
        return try {
            val function =
                Function(
                    GET_ERC20_BALANCE,
                    listOf(Address(address)),
                    listOf(object : TypeReference<Int256?>() {})
                )
            EthUtils.simpleEthCall(web3j, address, contractAddress, function)?.let {
                it as BigInteger
            }
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

}