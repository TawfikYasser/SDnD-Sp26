package solid.pacman.after;

public class PacMan extends GameCharacter {

    @Override
    public void move() {
        System.out.println("Move using keyboard");
    }

    @Override
    public void collide(GameCharacter other) {

        if (other instanceof Ghost ghost) {
            System.out.println(ghost.isFrightened() ? " Pac-Man eats ghost" : " Pac-Man dies");
        }
    }
}
