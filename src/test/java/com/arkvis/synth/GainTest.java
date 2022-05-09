package com.arkvis.synth;

import com.arkvis.synth.gain.DefaultGain;
import com.arkvis.synth.gain.Gain;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GainTest {

    @Test
    void should_returnCorrectValue_when_adjustingGain() {
        TestSynthInput listener = new TestSynthInput();
        Gain gain = new DefaultGain(0);
        gain.addListener(listener);

        TestSynthOutput output = new TestSynthOutput();
        output.addListener(gain);

        TestClock clock = new TestClock();
        clock.addComponent(output);
        clock.addComponent(gain);

        output.setValue(100);
        clock.trigger();
        assertEquals(0, listener.getValue());

        gain.setLevel(1);
        clock.trigger();
        assertEquals(100, listener.getValue());

        gain.setLevel(0.3);
        clock.trigger();
        assertEquals(30, listener.getValue());
    }
}
