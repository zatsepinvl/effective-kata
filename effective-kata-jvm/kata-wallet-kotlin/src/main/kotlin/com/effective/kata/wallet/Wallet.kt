package com.effective.kata.wallet

import java.math.BigDecimal
import java.math.BigDecimal.ZERO
import java.util.Currency

class Wallet {
    private val stocks: MutableMap<StockType, BigDecimal> = HashMap()

    fun addStock(stock: Stock): Wallet {
        val currentQuantity = stocks[stock.type] ?: ZERO
        val newQuantity = currentQuantity.add(stock.quantity)
        stocks[stock.type] = newQuantity
        return this
    }

    fun value(currency: Currency, rateProvider: RateProvider): BigDecimal {
        return stocks.map { (key, value) ->
            val price = rateProvider.getRate(key, currency)
            price.multiply(value)
        }.reduce(BigDecimal::add) ?: ZERO
    }
}