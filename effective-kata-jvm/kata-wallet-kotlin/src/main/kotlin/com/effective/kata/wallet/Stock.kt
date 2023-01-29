package com.effective.kata.wallet

import java.math.BigDecimal

data class Stock(
        val type: StockType,
        val quantity: BigDecimal
)