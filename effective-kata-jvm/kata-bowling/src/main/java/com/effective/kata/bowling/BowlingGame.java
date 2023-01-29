package com.effective.kata.bowling;

import java.util.ArrayList;
import java.util.List;

public class BowlingGame {
    public static final int FRAME_COUNT = 10;

    private final List<Frame> frames = new ArrayList<>(FRAME_COUNT);
    private int currentFrameIndex = 0;

    public void roll(int knockedPins) {
        Frame frame;
        if (frames.size() == currentFrameIndex) {
            frame = new Frame(currentFrameIndex + 1);
            frames.add(currentFrameIndex, frame);
            if (currentFrameIndex > 0) {
                frames.get(currentFrameIndex - 1).setNextFrame(frame);
            }
        } else {
            frame = frames.get(currentFrameIndex);
        }
        frame.roll(knockedPins);
        if (frame.isCompleted()) {
            currentFrameIndex++;
        }
    }

    public int totalScore() {
        return frames.stream()
                .map(Frame::finalScore)
                .reduce(0, Integer::sum);
    }

    public boolean isGameFinished() {
        Frame lastFrame = frames.get(9);
        return lastFrame != null && lastFrame.isCompleted();
    }
}
