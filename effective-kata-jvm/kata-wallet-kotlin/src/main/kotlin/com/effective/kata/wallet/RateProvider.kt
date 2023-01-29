package com.effective.kata.wallet

import java.math.BigDecimal
import java.util.Currency

interface RateProvider {
    fun getRate(type: StockType, currency: Currency): BigDecimal
}
