package com.arkvis.synth;

import com.arkvis.synth.gain.DefaultGain;
import com.arkvis.synth.gain.Gain;

class SoundTest {

    public static void main(String[] args) {
        int sampleRate = 8000;

        Gain gain1 = new DefaultGain(0.2);
        Gain gain2 = new DefaultGain(0.2);
        Gain gain3 = new DefaultGain(0.2);
        Gain gain4 = new DefaultGain(0.2);

        Oscillator oscillator1 = new SineWaveOscillator(sampleRate, 261);
        oscillator1.addListener(gain1);

        Oscillator oscillator2 = new SineWaveOscillator(sampleRate, 329);
        oscillator2.addListener(gain2);

        Oscillator oscillator3 = new SineWaveOscillator(sampleRate, 392);
        oscillator3.addListener(gain3);

        Oscillator oscillator4 = new SineWaveOscillator(sampleRate, 493);
        oscillator4.addListener(gain4);

        SynthInput speaker = new DefaultSpeaker(sampleRate);
        gain1.addListener(speaker);
        gain2.addListener(speaker);
        gain3.addListener(speaker);
        gain4.addListener(speaker);

        SynthClock clock = new DefaultClock();
        clock.addComponent(oscillator1);
        clock.addComponent(oscillator2);
        clock.addComponent(oscillator3);
        clock.addComponent(oscillator4);
        clock.addComponent(gain1);
        clock.addComponent(gain2);
        clock.addComponent(gain3);
        clock.addComponent(gain4);
        clock.addComponent(speaker);
        clock.start();
    }
}
