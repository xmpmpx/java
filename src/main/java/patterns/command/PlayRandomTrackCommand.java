package patterns.command;

public class PlayRandomTrackCommand implements MusicPlayerCommand {

    MusicPlayer musicPlayer;

    public PlayRandomTrackCommand(MusicPlayer musicPlayer) {
        this.musicPlayer = musicPlayer;
    }

    @Override
    public void play() {
        musicPlayer.playRandomTrack();
    }
}
