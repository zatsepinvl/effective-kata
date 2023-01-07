package com.effective.kata.wallet;

import java.util.Objects;

public class StockType {
    private final String stockType;

    private StockType(String stockType) {
        this.stockType = stockType;
    }

    public static StockType getInstance(String type) {
        return new StockType(type);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StockType stockType1 = (StockType) o;
        return Objects.equals(stockType, stockType1.stockType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stockType);
    }
}
