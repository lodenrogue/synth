package com.arkvis.synth.oscillator;

class SineWave {
    static final int MAX_VALUE = 127;

    private final int sampleRate;
    private final double frequency;

    SineWave(int sampleRate, double frequency) {
        this.sampleRate = sampleRate;
        this.frequency = frequency;
    }

    double calculateSineValue(int time) {
        double samplingInterval = sampleRate / frequency;
        double angle = (2.0 * Math.PI * time) / samplingInterval;
        return Math.sin(angle) * MAX_VALUE;
    }

    boolean isAtPositiveAlternationPoint(int time) {
        if (time > 0) {
            double newValue = calculateSineValue(time + 1);
            double prevValue = calculateSineValue(time);
            return newValue == 0 && prevValue < 0;
        }
        return false;
    }
}
