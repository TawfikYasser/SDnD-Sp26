package solid.pacman.after;

public abstract class GameCharacter {
    protected int health = 100;

    public abstract void move();

    public abstract void collide(GameCharacter other);
}
