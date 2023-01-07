package com.effective.kata.wallet;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.HashMap;
import java.util.Map;

public class Wallet {
    private final RateProvider rateProvider;
    private final Map<StockType, BigDecimal> stocks;

    public Wallet(RateProvider rateProvider) {
        this.rateProvider = rateProvider;
        this.stocks = new HashMap<>();
    }

    public BigDecimal value(Currency currency) {
        return stocks.entrySet().stream()
                .map(entry -> {
                    StockType stockType = entry.getKey();
                    BigDecimal quantity = entry.getValue();
                    BigDecimal rate = rateProvider.getRate(stockType, currency);
                    return quantity.multiply(rate);
                })
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public void addStock(Stock stock) {
        BigDecimal currentQuantity = stocks.getOrDefault(stock.type(), BigDecimal.ZERO);
        stocks.put(stock.type(), currentQuantity.add(stock.quantity()));
    }
}
