package solid.pacman.before;

public class PacmanBefore {

    static class GameCharacter {

        String type;
        int health;
        boolean frightened;

        public GameCharacter(String type) {
            this.type   = type;
            this.health = 100;
        }

        public void move() {
            if (type.equals("PacMan")) {
                System.out.println("Move using keyboard");
            } else if (type.equals("Blinky")) {
                System.out.println("Chase Pac-Man");
            } else if (type.equals("Pinky")) {
                System.out.println("Ambush Pac-Man");
            }
        }

        public void collide(GameCharacter other) {
            if (type.equals("PacMan") && other.type.contains("Ghost")) {
                if (other.frightened) {
                    System.out.println("Pac-Man eats ghost");
                } else {
                    System.out.println("Pac-Man dies");
                }
            }
        }

        public void saveScore() {
            System.out.println("Saving score to file...");
        }
    }

    public static void run() {
        GameCharacter pacman = new GameCharacter("PacMan");
        GameCharacter blinky = new GameCharacter("Blinky");

        pacman.move();
        blinky.move();

        blinky.frightened = true;
        pacman.collide(blinky);

        pacman.saveScore();
    }
}
