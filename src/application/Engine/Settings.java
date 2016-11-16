package application.Engine;

public class Settings {

    private boolean music;
    private boolean autoSave;

    public Settings() {
        this.music = false;
        this.autoSave = false;
    }


    public boolean isMusic() {
        return music;
    }

    public void setMusic(boolean music) {
        this.music = music;
    }

    public boolean isAutoSave() {
        return autoSave;
    }

    public void setAutoSave(boolean autoSave) {
        this.autoSave = autoSave;
    }
}
