package com.effective.kata.bowling;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BowlingGameTest {

    private BowlingGame game;

    @Test
    void testAll1() {
        // Given
        game = new BowlingGame();

        // When
        for (int i = 0; i < 20; i++) {
            game.roll(1);
        }

        // Then
        assertThat(game.isGameFinished()).isTrue();
        assertThat(game.totalScore()).isEqualTo(20);
    }

    @Test
    void testAllStrikes() {
        // Given
        game = new BowlingGame();

        // When
        for (int i = 0; i < 12; i++) {
            game.roll(10);
        }

        // Then
        assertThat(game.isGameFinished()).isTrue();
        assertThat(game.totalScore()).isEqualTo(300);
    }

    @Test
    void testAllSpares() {
        // Given
        game = new BowlingGame();

        // When
        for (int i = 0; i < 10; i++) {
            game.roll(5);
            game.roll(5);
        }
        game.roll(5);

        // Then
        assertThat(game.isGameFinished()).isTrue();
        assertThat(game.totalScore()).isEqualTo(150);
    }
}
