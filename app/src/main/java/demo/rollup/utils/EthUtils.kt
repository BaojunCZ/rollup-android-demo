package demo.rollup.utils

import java.math.BigDecimal
import java.math.BigInteger

object EthUtils {
    fun formatToEth(value: BigInteger): Float {
        return value.toBigDecimal().divide(BigDecimal.TEN.pow(18)).stripTrailingZeros().toFloat()
    }
}