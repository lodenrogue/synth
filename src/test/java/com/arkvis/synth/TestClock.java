package com.arkvis.synth;

import java.util.ArrayList;
import java.util.List;

public class TestClock implements SynthClock {

    private final List<Component> components;

    public TestClock() {
        components = new ArrayList<>();
    }

    @Override
    public void addComponent(Component component) {
        components.add(component);
    }

    @Override
    public void start() {

    }

    public void trigger() {
        components.forEach(Component::trigger);
    }
}
