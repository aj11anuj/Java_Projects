import java.util.Random;
import java.util.Scanner;

public class Game {
    private GameItem[][] board = new GameItem[4][4];
    private int playerRow, playerCol;

    // Method to randomly set up the board
    private void setBoard() {
        Random rand = new Random();
        // Place the Wumpus
        placeItem(new Wumpus());
        // Place pits (3 of them)
        for (int i = 0; i < 3; i++) {
            placeItem(new Pit());
        }
        // Place 1 to 3 pieces of gold
        int goldCount = rand.nextInt(3) + 1;
        for (int i = 0; i < goldCount; i++) {
            placeItem(new Gold());
        }
        // Fill remaining spaces with ClearGround
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (board[i][j] == null) {
                    board[i][j] = new ClearGround();
                }
            }
        }
        // Place the player at a ClearGround position
        do {
            playerRow = rand.nextInt(4);
            playerCol = rand.nextInt(4);
        } while (!(board[playerRow][playerCol] instanceof ClearGround));
    }

    // Method to place an item randomly
    private void placeItem(GameItem item) {
        Random rand = new Random();
        int row, col;
        do {
            row = rand.nextInt(4);
            col = rand.nextInt(4);
        } while (board[row][col] != null);
        board[row][col] = item;
    }

    // Method to display the board
    private void display() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (i == playerRow && j == playerCol) {
                    System.out.print("* ");
                } else {
                    System.out.print(board[i][j].display() + " ");
                }
            }
            System.out.println();
        }
    }

    // Method to sense nearby items
    private void senseNearby() {
        if (playerRow > 0 && board[playerRow - 1][playerCol] instanceof Pit) {
            System.out.println("You feel a breeze.");
        }
        if (playerRow < 3 && board[playerRow + 1][playerCol] instanceof Pit) {
            System.out.println("You feel a breeze.");
        }
        if (playerCol > 0 && board[playerRow][playerCol - 1] instanceof Gold) {
            System.out.println("You see a faint glitter.");
        }
        if (playerCol < 3 && board[playerRow][playerCol + 1] instanceof Wumpus) {
            System.out.println("You smell a vile stench.");
        }
    }

    // Method to display the menu and move the player
    private void menu() {
        Scanner sc = new Scanner(System.in);
        System.out.println("=====Wumpus=====");
        System.out.println("1. Move player left");
        System.out.println("2. Move player right");
        System.out.println("3. Move player up");
        System.out.println("4. Move player down");
        System.out.println("5. Quit");
        int choice = sc.nextInt();
        switch (choice) {
            case 1: // Move left
                playerCol = (playerCol == 0) ? 3 : playerCol - 1;
                break;
            case 2: // Move right
                playerCol = (playerCol == 3) ? 0 : playerCol + 1;
                break;
            case 3: // Move up
                playerRow = (playerRow == 0) ? 3 : playerRow - 1;
                break;
            case 4: // Move down
                playerRow = (playerRow == 3) ? 0 : playerRow + 1;
                break;
            case 5: // Quit
                System.exit(0);
        }
    }

    // Method to run the game
    public void runGame() {
        setBoard();
        while (true) {
            display();
            senseNearby();
            menu();
            checkGameStatus();
        }
    }

    private void checkGameStatus() {
        if (board[playerRow][playerCol] instanceof Pit) {
            System.out.println("You fell into a pit! Game over.");
            System.exit(0);
        } else if (board[playerRow][playerCol] instanceof Wumpus) {
            System.out.println("The Wumpus got you! Game over.");
            System.exit(0);
        } else if (board[playerRow][playerCol] instanceof Gold) {
            System.out.println("You found gold!");
            board[playerRow][playerCol] = new ClearGround();
        }
    }
}

