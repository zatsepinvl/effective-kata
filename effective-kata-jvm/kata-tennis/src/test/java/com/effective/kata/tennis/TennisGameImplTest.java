package com.effective.kata.tennis;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TennisGameImplTest {

    private TennisGame game;

    @BeforeEach
    void setUp() {
        game = new TennisGameImpl();
    }

    @Test
    void simple_game() {
        // When
        game.winPoint(Player.ONE); // LOVE - 0
        game.winPoint(Player.ONE); // 15 - 0
        game.winPoint(Player.TWO); // 15 - LOVE
        game.winPoint(Player.ONE); // 30 - LOVE
        game.winPoint(Player.TWO); // 30 - 15
        game.winPoint(Player.ONE); // 45 - 15
        game.winPoint(Player.ONE); // WIN - 15

        // Then
        assertThat(game.getWinner()).contains(Player.ONE);
    }

    @Test
    void is_deuce_on_p40() {
        // Given
        givenPlayerScore(game, Player.ONE, Score.P40);
        givenPlayerScore(game, Player.TWO, Score.P40);

        // Then
        assertThat(game.isDeuce()).isTrue();
    }

    @Test
    void deuce_winner_player_1() {
        // Given
        givenPlayerScore(game, Player.ONE, Score.P40);
        givenPlayerScore(game, Player.TWO, Score.P40);

        // When
        game.winPoint(Player.ONE);
        game.winPoint(Player.ONE);

        // Then
        assertThat(game.getWinner()).contains(Player.ONE);
    }

    @Test
    void deuce_winner_player_2() {
        // Given
        givenPlayerScore(game, Player.ONE, Score.P40);
        givenPlayerScore(game, Player.TWO, Score.P40);

        // When
        game.winPoint(Player.ONE);
        game.winPoint(Player.TWO);
        game.winPoint(Player.TWO);

        // Then
        assertThat(game.getWinner()).contains(Player.TWO);
    }

    private void givenPlayerScore(TennisGame game, Player player, Score targetScore) {
        while (game.getCurrentScore(player) != targetScore) {
            game.winPoint(player);
        }
    }

}
