package patterns.command;

public class RemoteController {

    MusicPlayerCommand musicPlayerCommand;

    public void pressButton() {
        musicPlayerCommand.play();
    }

    public void setMusicPlayerCommand(MusicPlayerCommand musicPlayerCommand) {
        this.musicPlayerCommand = musicPlayerCommand;
    }
}
