package com.arkvis.synth.gain;

import com.arkvis.synth.SynthInput;

import java.util.ArrayList;
import java.util.List;

public class DefaultGain implements Gain {
    private final List<SynthInput> listeners;
    private double level;
    private double value;

    public DefaultGain(double level) {
        this.level = level;
        listeners = new ArrayList<>();
    }

    @Override
    public void trigger() {
        listeners.forEach(listener -> listener.feed(value * level));
    }

    @Override
    public void feed(double value) {
        this.value = value;
    }

    @Override
    public void addListener(SynthInput listener) {
        listeners.add(listener);
    }

    @Override
    public void setLevel(double level) {
        this.level = level;
    }
}
