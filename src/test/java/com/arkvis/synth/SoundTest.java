package com.arkvis.synth;

public class SoundTest {

    public static void main(String[] args) {
        int sampleRate = 8000;
        SynthInput speaker = new DefaultSpeaker(sampleRate);

        Oscillator oscillator1 = new SineWaveOscillator(sampleRate, 261);
        oscillator1.addListener(speaker);

        Oscillator oscillator2 = new SineWaveOscillator(sampleRate, 329);
        oscillator2.addListener(speaker);

        Oscillator oscillator3 = new SineWaveOscillator(sampleRate, 392);
        oscillator3.addListener(speaker);

        Oscillator oscillator4 = new SineWaveOscillator(sampleRate, 493);
        oscillator4.addListener(speaker);

        SynthClock clock = new DefaultClock();
        clock.addComponent(oscillator1);
        clock.addComponent(oscillator2);
        clock.addComponent(oscillator3);
        clock.addComponent(oscillator4);
        clock.addComponent(speaker);
        clock.start();
    }
}
