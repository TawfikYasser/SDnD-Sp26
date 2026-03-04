package solid.pacman;

import solid.pacman.after.*;
import solid.pacman.before.GameCharacter;

public class Main {

    public static void main(String[] args) {
        runBefore();
        runAfter();
    }

    static void runBefore() {

        GameCharacter pacman = new GameCharacter("PacMan");
        GameCharacter blinky = new GameCharacter("Blinky");

        pacman.move();
        blinky.move();

        blinky.frightened = true;
        pacman.collide(blinky);

        pacman.saveScore();

    }

    static void runAfter() {

        PacMan pacman = new PacMan();
        Blinky blinky = new Blinky();
        Pinky  pinky  = new Pinky();

        pacman.move();
        blinky.move();
        pinky.move();

        blinky.setFrightened(true);
        pacman.collide(blinky);

        blinky.setFrightened(false);
        pacman.collide(blinky);

        new ScoreService().saveScore();

    }
}
