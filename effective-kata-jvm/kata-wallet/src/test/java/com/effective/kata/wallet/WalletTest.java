package com.effective.kata.wallet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class WalletTest {

    // Currencies
    private static final Currency USD = Currency.getInstance("USD");
    private static final Currency EUR = Currency.getInstance("EUR");

    // Stock types
    private static final StockType DOLLARS = StockType.getInstance("Dollar");
    private static final StockType BITCOINS = StockType.getInstance("Bitcoin");

    static class TestRateProvider implements RateProvider {
        private final Map<StockType, Map<Currency, BigDecimal>> rates = new HashMap<>();

        void setRate(StockType type, Currency currency, BigDecimal value) {
            Map<Currency, BigDecimal> typeRates = rates.getOrDefault(type, new HashMap<>());
            typeRates.put(currency, value);
            rates.put(type, typeRates);
        }

        @Override
        public BigDecimal getRate(StockType stockType, Currency currency) {
            return rates.get(stockType).get(currency);
        }
    }

    private Wallet wallet;
    private TestRateProvider rateProvider;

    @BeforeEach
    void setUp() {
        rateProvider = new TestRateProvider();
        wallet = new Wallet(rateProvider);
    }

    @Test
    void valueOfSingeStock() {
        // Given
        rateProvider.setRate(DOLLARS, USD, BigDecimal.ONE);
        wallet.addStock(new Stock(DOLLARS, BigDecimal.TEN));

        // When
        var value = wallet.value(USD);

        // Then
        assertThat(value).isEqualTo(BigDecimal.TEN);
    }

    @Test()
    void valueOfSingleStockInManyCurrencies() {
        // Given
        rateProvider.setRate(DOLLARS, USD, BigDecimal.valueOf(1.1));
        rateProvider.setRate(DOLLARS, EUR, BigDecimal.valueOf(1));
        wallet.addStock(new Stock(DOLLARS, BigDecimal.TEN));

        // When
        var valueUsd = wallet.value(USD);
        var valueEur = wallet.value(EUR);

        // Then
        assertThat(valueUsd).isEqualByComparingTo(BigDecimal.valueOf(11));
        assertThat(valueEur).isEqualByComparingTo(BigDecimal.valueOf(10));
    }

    @Test
    void valueOfManyStocks() {
        // Given
        rateProvider.setRate(DOLLARS, USD, BigDecimal.valueOf(0.5));
        rateProvider.setRate(BITCOINS, USD, BigDecimal.valueOf(1.115));
        wallet.addStock(new Stock(DOLLARS, BigDecimal.TEN)); // total = $5
        wallet.addStock(new Stock(BITCOINS, BigDecimal.valueOf(1))); // ->
        wallet.addStock(new Stock(BITCOINS, BigDecimal.valueOf(9))); // total = $11.15

        // When
        var value = wallet.value(USD);

        // Then
        assertThat(value).isEqualByComparingTo(BigDecimal.valueOf(16.15));
    }
}
