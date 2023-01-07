package com.effective.kata.wallet;

import java.math.BigDecimal;

public record Stock(
        StockType type,
        BigDecimal quantity
) {
}
