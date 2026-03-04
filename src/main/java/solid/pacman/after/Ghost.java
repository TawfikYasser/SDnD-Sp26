package solid.pacman.after;

public abstract class Ghost extends GameCharacter {

    protected boolean frightened;

    public boolean isFrightened() { return frightened; }

    public void setFrightened(boolean val) { this.frightened = val; }

    @Override
    public void collide(GameCharacter other) {

    }
}
