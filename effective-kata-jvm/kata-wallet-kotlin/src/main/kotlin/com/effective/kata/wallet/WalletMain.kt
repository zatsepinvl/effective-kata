package com.effective.kata.wallet

import java.math.BigDecimal.ONE
import java.util.*


val USD = StockType("USD")
val EUR = StockType("EUR")
val GOLD = StockType("GOLD")

fun main() {
    val rateProvider = MockRateProvider()

    val wallet = Wallet()
            .addStock(Stock(USD, ONE))
            .addStock(Stock(EUR, ONE))
            .addStock(Stock(GOLD, ONE))

    val currency = Currency.getInstance("USD")
    val value = wallet.value(currency, rateProvider)

    println("Total wallet value in $currency: $value")
}