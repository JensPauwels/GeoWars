package application.Engine;

/**
 * Created by jens on 13/10/16.
 */
public class Settings {

    private boolean music;
    private boolean autoSave;

    public Settings(){
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
