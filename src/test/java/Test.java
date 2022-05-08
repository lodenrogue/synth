import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.SourceDataLine;

public class Test {

    private static int sampleRate = 8000;

    public static void main(String[] args) {
        final AudioFormat af = new AudioFormat(sampleRate, 16, 1, true, true);
        try {
            SourceDataLine line = AudioSystem.getSourceDataLine(af);
            line.open(af);
            line.start();

            byte[] wave = generateSineWave(220, 1, line);
//            line.write(wave, 0, wave.length);

            byte[] secondWave = generateSineWave(440, 3, line);
//            line.write(secondWave, 0, wave.length);
            line.drain();
            line.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static byte[] generateSineWave(int frequencyOfSignal, int seconds, SourceDataLine line) {
        // total samples = (duration in second) * (samples per second)
        byte[] sin = new byte[seconds * sampleRate];
        double samplingInterval = (double) (sampleRate / frequencyOfSignal);

        System.out.println("Sampling Frequency  : " + sampleRate);
        System.out.println("Frequency of Signal : " + frequencyOfSignal);
        System.out.println("Sampling Interval   : " + samplingInterval);

        for (int i = 0; i < sin.length; i++) {
            double angle = (2.0 * Math.PI * i) / samplingInterval;
            System.out.println((Math.sin(angle) * 127));
            sin[i] = (byte) (Math.sin(angle) * 127);

            line.write(new byte[]{sin[i], sin[i]}, 0, 2);

//            if(i > 0 && i % 2 != 0) {
//                line.write(new byte[]{sin[i-1], sin[i]}, 0, 2);
//            }
        }

        for(int i = 0; i < 36; i++) {
            String point = String.format("(%s,%s),", i*7.5, sin[i]);
            System.out.print(point);
        }
        System.out.println();
        return sin;
    }
}
