package com.arkvis.synth.gain;

import com.arkvis.synth.SynthInput;
import com.arkvis.synth.SynthOutput;

public interface Gain extends SynthInput, SynthOutput {

    void setLevel(double level);
}
