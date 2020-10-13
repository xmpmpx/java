package patterns.command;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class MusicPlayer {

    List<String> tracks = Arrays.asList("Track 1", "Track 2", "Track 3");

    int currentTrackNumber = 0;

    void playFirstTrack() {
        currentTrackNumber = 0;
        System.out.println("Playing " + tracks.get(currentTrackNumber));
    }

    void playNextTrack() {
        currentTrackNumber++;
        if (currentTrackNumber > 2) {
            currentTrackNumber = 0;
        }
        System.out.println("Playing " + tracks.get(currentTrackNumber));
    }

    void playRandomTrack() {
        int size = tracks.size();
        Random random = new Random();
        currentTrackNumber = random.nextInt(size);
        System.out.println("Playing " + tracks.get(currentTrackNumber));
    }
}
