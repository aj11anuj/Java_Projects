import java.util.*;

public class Game {
    GameItem[][] board = new GameItem[4][4];
    int row, col;
    boolean gameOver = false;

    // Method to randomly set up the board
    void setBoard() {
        Random rand = new Random();
        placeItem(new Wumpus());
        placeItem(new Pit());
        placeItem(new Gold());

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (board[i][j] == null) {
                    board[i][j] = new ClearGround();
                }
            }
        }

        do {
            row = rand.nextInt(4);
            col = rand.nextInt(4);
        } while (!(board[row][col] instanceof ClearGround));
    }

    // Method to place an item randomly
    void placeItem(GameItem item) {
        Random rand = new Random();
        int r, c;
        do {
            r = rand.nextInt(4);
            c = rand.nextInt(4);
        } while (board[r][c] != null);
        board[r][c] = item;
    }

    // Method to display the board
    void display() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (i == row && j == col) {
                    System.out.print("* ");
                } else {
                    System.out.print(board[i][j].display() + " ");
                }
            }
            System.out.println();
        }
    }

    // Method to display the menu and move the player
    void menu() {
        Scanner sc = new Scanner(System.in);
        System.out.println("1. Left  2. Right  3. Up  4. Down");
        int choice = sc.nextInt();
        switch (choice) {
            case 1 -> col = (col == 0) ? 3 : col - 1;
            case 2 -> col = (col == 3) ? 0 : col + 1;
            case 3 -> row = (row == 0) ? 3 : row - 1;
            case 4 -> row = (row == 3) ? 0 : row + 1;
        }
    }

    // Method to check game status
    void status() {
        if (board[row][col] instanceof Pit) {
            System.out.println("You fell into a pit! Game over.");
            gameOver = true;
        } else if (board[row][col] instanceof Wumpus) {
            System.out.println("The Wumpus got you! Game over.");
            gameOver = true;
        } else if (board[row][col] instanceof Gold) {
            System.out.println("You found gold!");
            board[row][col] = new ClearGround();
        }
    }

    // Method to run the game
    public void runGame() {
        setBoard();
        while (!gameOver) {
            display();
            menu();
            status(); 
        }
    }
}
