package com.effective.kata.tennis;

public enum Score {
    NONE,
    LOVE,
    P15,
    P30,
    P40;

    public Score next() {
        return switch (this) {
            case NONE -> LOVE;
            case LOVE -> P15;
            case P15 -> P30;
            case P30 -> P40;
            case P40 -> throw new IllegalArgumentException("P40 is a final score");
        };
    }
}
