package patterns.command;

public class PlayNextTrackCommand implements MusicPlayerCommand {

    MusicPlayer musicPlayer;

    public PlayNextTrackCommand(MusicPlayer musicPlayer) {
        this.musicPlayer = musicPlayer;
    }

    @Override
    public void play() {
        musicPlayer.playNextTrack();
    }
}
