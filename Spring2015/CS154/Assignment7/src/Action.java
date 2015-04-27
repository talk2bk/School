
class Action {
    //triple of ints
    private int newState;
    private char newSymbol;
    private int cellsToMove;//negative means move to left, 0 means stay, positive means move to right
    //arbitrary amount, moving to the left of 0 causes program to halt
    
    public Action(int x, char y, int z){
        newState = x; newSymbol = y; cellsToMove = z;
    }

    /**
     * @return the newState
     */
    public int getNewState() {
        return newState;
    }

    /**
     * @return the newSymbol
     */
    public char getNewSymbol() {
        return newSymbol;
    }

    /**
     * @return the cellsToMove
     */
    public int getCellsToMove() {
        return cellsToMove;
    }
}
