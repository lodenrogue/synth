package com.arkvis.synth.speaker;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.SourceDataLine;

public class DefaultSpeaker implements Speaker {
    private final SourceDataLine line;
    private double sum;

    public DefaultSpeaker(int sampleRate) {
        line = createLine(sampleRate);
    }

    @Override
    public void feed(double value) {
        sum += value;
    }

    @Override
    public void trigger() {
        byte b = (byte) sum;
        line.write(new byte[]{b, b}, 0, 2);
        sum = 0;
    }

    private SourceDataLine createLine(int sampleRate) {
        try {
            AudioFormat audioFormat = new AudioFormat(sampleRate, 16, 1, true, true);

            SourceDataLine line = AudioSystem.getSourceDataLine(audioFormat);
            line.open(audioFormat);
            line.start();

            return line;
        } catch (Exception ex) {
            throw new RuntimeException("Error creating audio source data line", ex);
        }
    }
}
