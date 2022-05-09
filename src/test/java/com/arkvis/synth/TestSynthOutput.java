package com.arkvis.synth;

import java.util.ArrayList;
import java.util.List;

public class TestSynthOutput implements SynthOutput {

    private double value;
    private final List<SynthInput> listeners;

    public TestSynthOutput() {
        listeners = new ArrayList<>();
    }

    @Override
    public void trigger() {
        listeners.forEach(listener -> listener.feed(value));
    }

    @Override
    public void addListener(SynthInput listener) {
        listeners.add(listener);

    }

    public void setValue(double value) {
        this.value = value;
    }
}
