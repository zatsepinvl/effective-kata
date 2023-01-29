package com.effective.kata.wallet

import java.math.BigDecimal
import java.math.BigDecimal.valueOf
import java.util.*
import java.util.Currency.getInstance

class MockRateProvider : RateProvider {
    override fun getRate(type: StockType, currency: Currency): BigDecimal {
        return when (type) {
            StockType("USD") -> valueOf(1)
            StockType("EUR") -> valueOf(1.03)
            else -> valueOf(0)
        }
    }
}