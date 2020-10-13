package patterns.command;

public class PlayFirstTrackCommand implements MusicPlayerCommand {

    MusicPlayer musicPlayer;

    public PlayFirstTrackCommand(MusicPlayer musicPlayer) {
        this.musicPlayer = musicPlayer;
    }

    @Override
    public void play() {
        musicPlayer.playFirstTrack();
    }
}
