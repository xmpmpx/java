package patterns.command;

public class CommandMain {
    public static void main(String[] args) {
        MusicPlayer musicPlayer = new MusicPlayer();
        RemoteController remote = new RemoteController();
        remote.setMusicPlayerCommand(new PlayFirstTrackCommand(musicPlayer));
        remote.pressButton();
        remote.setMusicPlayerCommand(new PlayNextTrackCommand(musicPlayer));
        remote.pressButton();
        remote.pressButton();
        remote.pressButton();
        remote.setMusicPlayerCommand(new PlayRandomTrackCommand(musicPlayer));
        remote.pressButton();
        remote.pressButton();
    }
}
