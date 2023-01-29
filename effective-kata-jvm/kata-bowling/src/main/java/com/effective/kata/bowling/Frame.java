package com.effective.kata.bowling;

import static com.effective.kata.bowling.FrameStage.*;

public class Frame {
    public static final int LAST_FRAME_NUMBER = 10;
    public static final int STRIKE_SCORE = 10;
    public static final int SPARE_SCORE = 10;

    private final boolean lastFrame;

    private int firstRoll;
    private int secondRoll;
    private int firstBonusRoll;
    private int secondBonusRoll;

    private Frame nextFrame;

    private FrameStage stage;

    public Frame(int number) {
        this.lastFrame = number == LAST_FRAME_NUMBER;
        stage = FrameStage.FIRST_ROLL;
    }

    public void roll(int knockedPins) {
        switch (stage) {
            case FIRST_ROLL -> {
                firstRoll = knockedPins;
                if (lastFrame && isStrike()) {
                    stage = FIRST_BONUS_ROLL;
                } else if (isStrike()) {
                    stage = COMPLETED;
                } else {
                    stage = SECOND_ROLL;
                }
            }
            case SECOND_ROLL -> {
                secondRoll = knockedPins;
                if (lastFrame && isSpare()) {
                    stage = FIRST_BONUS_ROLL;
                } else {
                    stage = COMPLETED;
                }
            }
            case FIRST_BONUS_ROLL -> {
                firstBonusRoll = knockedPins;
                if (isStrike()) {
                    stage = SECOND_BONUS_ROLL;
                } else {
                    stage = COMPLETED;
                }
            }
            case SECOND_BONUS_ROLL -> {
                secondBonusRoll = knockedPins;
                stage = COMPLETED;
            }
            case COMPLETED -> {
                throw new IllegalStateException("Frame is completed.");
            }
        }
    }

    public int finalScore() {
        if (lastFrame) {
            return rollSum() + firstBonusRoll + secondBonusRoll;
        }
        if (isSpare()) {
            return rollSum() + nextFrame.getFirstRoll();
        }
        if (isStrike() && nextFrame.isStrike()) {
            if (nextFrame.isLastFrame()) {
                return rollSum() + nextFrame.getFirstRoll() + nextFrame.firstBonusRoll;
            }
            return rollSum() + nextFrame.getFirstRoll() + nextFrame.nextFrame.getFirstRoll();
        }
        return rollSum();
    }

    public boolean isSpare() {
        return firstRoll < SPARE_SCORE && rollSum() == SPARE_SCORE;
    }

    public boolean isStrike() {
        return firstRoll == STRIKE_SCORE;
    }

    private int rollSum() {
        return firstRoll + secondRoll;
    }

    public int getFirstRoll() {
        return firstRoll;
    }

    public int getSecondRoll() {
        return secondRoll;
    }

    public boolean isCompleted() {
        return stage == COMPLETED;
    }

    public void setNextFrame(Frame nextFrame) {
        this.nextFrame = nextFrame;
    }

    public boolean isLastFrame() {
        return lastFrame;
    }
}
