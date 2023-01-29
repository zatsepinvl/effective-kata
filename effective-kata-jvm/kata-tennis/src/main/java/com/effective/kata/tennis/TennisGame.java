package com.effective.kata.tennis;

import java.util.Optional;

public interface TennisGame {
    void winPoint(Player player);

    Optional<Player> getWinner();

    Score getCurrentScore(Player player);

    boolean isDeuce();
}
