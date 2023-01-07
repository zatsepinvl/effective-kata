package com.effective.kata.wallet;

import java.math.BigDecimal;
import java.util.Currency;

public interface RateProvider {
    BigDecimal getRate(StockType stockType, Currency currency);
}
