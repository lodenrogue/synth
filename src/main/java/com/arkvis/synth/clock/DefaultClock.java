package com.arkvis.synth.clock;

import com.arkvis.synth.Component;

import java.util.ArrayList;
import java.util.List;

public class DefaultClock implements SynthClock {
    private final List<Component> components;

    public DefaultClock() {
        components = new ArrayList<>();
    }

    @Override
    public void addComponent(Component component) {
        components.add(component);
    }

    @Override
    public void start() {
        while (true) {
            components.forEach(Component::trigger);
        }
    }

}
