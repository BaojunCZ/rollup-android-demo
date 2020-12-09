package demo.rollup.utils

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.web3j.abi.FunctionEncoder
import org.web3j.abi.FunctionReturnDecoder
import org.web3j.abi.datatypes.Function
import org.web3j.protocol.Web3j
import org.web3j.protocol.core.DefaultBlockParameterName
import org.web3j.protocol.core.methods.request.Transaction
import java.math.BigDecimal
import java.math.BigInteger

object EthUtils {
    fun formatToEth(value: BigInteger): Float {
        return value.toBigDecimal().divide(BigDecimal.TEN.pow(18)).stripTrailingZeros().toFloat()
    }

    suspend fun simpleEthCall(
        web3j: Web3j,
        address: String,
        contractAddress: String,
        function: Function
    ): Any? {
        return withContext(Dispatchers.IO) {
            val transaction =
                Transaction.createEthCallTransaction(
                    address,
                    contractAddress,
                    FunctionEncoder.encode(function)
                )
            val ethCall = web3j.ethCall(transaction, DefaultBlockParameterName.LATEST).send()
            Log.e("ethCall", ethCall.value)
            val results = FunctionReturnDecoder.decode(ethCall.value, function.outputParameters)
            if (results.isNotEmpty()) {
                results[0].value
            } else {
                null
            }
        }
    }
}