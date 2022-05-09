package com.arkvis.synth;

import com.arkvis.synth.oscillator.Oscillator;
import com.arkvis.synth.oscillator.SineWaveOscillator;
import com.arkvis.synth.oscillator.SquareWaveOscillator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class OscillatorTest {

    @Test
    void should_returnCorrectValues_when_generatingSineWave() {
        TestSynthInput testListener = new TestSynthInput();
        Oscillator oscillator = new SineWaveOscillator(8000, 600);
        oscillator.addListener(testListener);

        TestClock clock = new TestClock();
        clock.addComponent(oscillator);

        long[] expectedPoints = new long[]{0, 58, 103, 125, 121, 90, 39, -20, -75, -113, -127, -113, -75, -20};
        long[] actualPoints = new long[expectedPoints.length];

        for (int i = 0; i < actualPoints.length; i++) {
            clock.trigger();
            actualPoints[i] = Math.round(testListener.getValue());
        }
        assertArrayEquals(expectedPoints, actualPoints);
    }

    @Test
    void should_returnCorrectValues_when_generatingSquareWave() {
        TestSynthInput testListener = new TestSynthInput();
        Oscillator oscillator = new SquareWaveOscillator(8000, 600);
        oscillator.addListener(testListener);

        TestClock clock = new TestClock();
        clock.addComponent(oscillator);

        long[] expectedPoints = new long[]{127, 127, 127, 127, 127, 127, 127, -127, -127, -127, -127, -127, -127, -127};
        long[] actualPoints = new long[expectedPoints.length];

        for (int i = 0; i < actualPoints.length; i++) {
            clock.trigger();
            actualPoints[i] = Math.round(testListener.getValue());
        }
        assertArrayEquals(expectedPoints, actualPoints);
    }
}
