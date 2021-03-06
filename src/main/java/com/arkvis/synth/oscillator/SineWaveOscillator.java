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

        SineWave sineWave = new SineWave(sampleRate, frequency);
        time = sineWave.isAtPositiveAlternationPoint(time) ? 0 : time + 1;
    }

    @Override
    public void addListener(SynthInput listener) {
        inputListeners.add(listener);
    }

    private double calculateSineValue(int time) {
        return new SineWave(sampleRate, frequency).calculateSineValue(time);
    }
}
