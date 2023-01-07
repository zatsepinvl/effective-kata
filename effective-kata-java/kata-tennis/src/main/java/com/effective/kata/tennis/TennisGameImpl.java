package com.effective.kata.tennis;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class TennisGameImpl implements TennisGame {
    private final Map<Player, Score> scores;
    private Player winner;
    private boolean deuce;
    private Player deuceAdvantagePlayer;

    public TennisGameImpl() {
        scores = new HashMap<>();
        scores.put(Player.ONE, Score.NONE);
        scores.put(Player.TWO, Score.NONE);
    }

    @Override
    public void winPoint(Player player) {
        if (winner != null) {
            throw new IllegalStateException("Game is over.");
        }

        if (scores.get(player) == Score.P40 && !deuce) {
            winner = player;
            return;
        }

        if (deuce) {
            playDeuce(player);
        } else {
            incrementScore(player);
            checkIfDeuce();
        }
    }

    private void incrementScore(Player player) {
        scores.computeIfPresent(player, (p, score) -> score.next());
    }

    private void checkIfDeuce() {
        if (scores.get(Player.ONE) == Score.P40 && scores.get(Player.TWO) == Score.P40) {
            deuce = true;
        }
    }

    private void playDeuce(Player player) {
        if (deuceAdvantagePlayer == player) {
            winner = player;
        } else {
            deuceAdvantagePlayer = player;
        }
    }

    @Override
    public Optional<Player> getWinner() {
        return Optional.ofNullable(winner);
    }

    @Override
    public Score getCurrentScore(Player player) {
        return scores.get(player);
    }

    @Override
    public boolean isDeuce() {
        return deuce;
    }
}
