package com.arkvis.synth;

import com.arkvis.synth.oscillator.Oscillator;
import com.arkvis.synth.oscillator.SineWaveOscillator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OscillatorTest {

    @Test
    void should_returnCorrectValues_when_generatingSineWave() {
        TestSynthInput testListener = new TestSynthInput();

        Oscillator oscillator = new SineWaveOscillator(8000, 220);
        oscillator.addListener(testListener);

        TestClock clock = new TestClock();
        clock.addComponent(oscillator);

        clock.trigger();
        assertEquals(0, Math.round(testListener.getValue()));

        clock.trigger();
        assertEquals(22, Math.round(testListener.getValue()));
    }
}
