package de.halirutan.keypromoterx;

import com.google.common.io.Resources;

import javax.sound.sampled.*;
import java.io.*;
import java.nio.file.Paths;

public class KeyPromoterSound {

    public void play(String filePath) throws IOException,
            UnsupportedAudioFileException, LineUnavailableException, InterruptedException {

        File file = new File(filePath);

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
        try (AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file)) {
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
