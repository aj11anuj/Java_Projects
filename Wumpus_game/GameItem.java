public abstract class GameItem {
    private char displayChar;

    public GameItem(char c) {
        this.displayChar = c;
    }

    public char display() {
        return displayChar;
    }
}

