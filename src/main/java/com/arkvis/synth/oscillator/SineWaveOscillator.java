package com.arkvis.synth.oscillator;

import com.arkvis.synth.SynthInput;

import java.util.ArrayList;
import java.util.List;

public class SineWaveOscillator implements Oscillator {
    private final int sampleRate;
    private final double frequency;
    private final List<SynthInput> inputListeners;
    private int time;

    public SineWaveOscillator(int sampleRate, double frequency) {
        this.sampleRate = sampleRate;
        this.frequency = frequency;
        this.inputListeners = new ArrayList<>();
        this.time = 0;
    }

    @Override
    public void trigger() {
        double value = calculateSineValue(time);
        inputListeners.forEach(listener -> listener.feed(value));
        time = isAtPositiveAlternationPoint() ? 0 : time + 1;
    }

    @Override
    public void addListener(SynthInput listener) {
        inputListeners.add(listener);
    }

    public boolean isAtPositiveAlternationPoint() {
        if (time > 0) {
            double newValue = calculateSineValue(time + 1);
            double prevValue = calculateSineValue(time);
            return newValue == 0 && prevValue < 0;
        }
        return false;
    }

    private double calculateSineValue(int time) {
        double samplingInterval = sampleRate / frequency;
        double angle = (2.0 * Math.PI * time) / samplingInterval;
        return Math.sin(angle) * 127;
    }
}
