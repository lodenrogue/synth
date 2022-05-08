package com.arkvis.synth;

public class TestSynthInput implements SynthInput {
    private double value = 0;

    public double getValue() {
        return value;
    }

    @Override
    public void feed(double value) {
        this.value = value;
    }

    @Override
    public void trigger() {

    }
}
