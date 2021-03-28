package de.halirutan.keypromoterx;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class KeyPromoterSound {

    public void play(InputStream input) throws IOException,
            UnsupportedAudioFileException, LineUnavailableException, InterruptedException {


        class AudioListener implements LineListener {
            private boolean done = false;

            @Override
            public synchronized void update(LineEvent event) {
                LineEvent.Type eventType = event.getType();
                if (eventType == LineEvent.Type.STOP || eventType == LineEvent.Type.CLOSE) {
                    done = true;
                    notifyAll();
                }
            }

            public synchronized void waitUntilDone() throws InterruptedException {
                while (!done) {
                    wait();
                }
            }
        }
        AudioListener listener = new AudioListener();
        try (AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(input)) {
            Clip clip = AudioSystem.getClip();
            try (clip) {
                clip.addLineListener(listener);
                clip.open(audioInputStream);
                clip.start();
                listener.waitUntilDone();
            }
        }
    }
}
