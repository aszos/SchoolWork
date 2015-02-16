package edu.udel.jatlas.tictactoe5x5;

public class Piece {
    public static final char EMPTY = ' ';
    public static final char BLOCKED = '#';
    
    private char symbol;

    public Piece(char symbol) {
        this.symbol = symbol;
    }
    
    public char getSymbol() {
        return symbol;
    }

    public boolean equals(Object obj) {
        return obj instanceof Piece && ((Piece)obj).symbol == symbol;
    }

    public String toString() {
        return Character.toString(symbol);
    }
    
    public boolean isEmpty() {
        return symbol == EMPTY;
    }
    
    public boolean isBlocked() {
        return symbol == BLOCKED;
    }
}
