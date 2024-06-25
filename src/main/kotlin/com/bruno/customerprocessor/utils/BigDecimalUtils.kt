package com.bruno.customerprocessor.utils

import java.math.BigDecimal
import java.math.RoundingMode
import kotlin.random.Random

class BigDecimalUtils {

    companion object {
        /**
         * Generates a random BigDecimal between the specified minValue and maxValue.
         *
         * @param minValue The minimum value (inclusive).
         * @param maxValue The maximum value (exclusive).
         * @return A random BigDecimal between minValue and maxValue.
         */
        @JvmStatic
        fun randomBetween(minValue: BigDecimal, maxValue: BigDecimal): BigDecimal {
            if (minValue >= maxValue) {
                throw IllegalArgumentException("minValue must be less than maxValue")
            }

            val randomDouble = Random.nextDouble(minValue.toDouble(), maxValue.toDouble())
            return BigDecimal.valueOf(randomDouble).setScale(2, RoundingMode.HALF_UP)
        }
    }

}