package com.arkvis.synth.oscillator;

import com.arkvis.synth.SynthInput;

import java.util.ArrayList;
import java.util.List;

public class SquareWaveOscillator implements Oscillator {
    private final int sampleRate;
    private final double frequency;
    private final List<SynthInput> inputListeners;
    private int time;

    public SquareWaveOscillator(int sampleRate, int frequency) {
        this.sampleRate = sampleRate;
        this.frequency = frequency;
        this.inputListeners = new ArrayList<>();
        this.time = 0;
    }

    @Override
    public void trigger() {
        double value = calculateSineValue(time);
        inputListeners.forEach(listener -> listener.feed(value));

        SineWave sineWave = new SineWave(sampleRate, frequency);
        time = sineWave.isAtPositiveAlternationPoint(time) ? 0 : time + 1;
    }

    @Override
    public void addListener(SynthInput listener) {
        inputListeners.add(listener);
    }

    private double calculateSineValue(int time) {
        double point = new SineWave(sampleRate, frequency).calculateSineValue(time);
        return point >= 0 ? SineWave.MAX_VALUE : -SineWave.MAX_VALUE;
    }
}
