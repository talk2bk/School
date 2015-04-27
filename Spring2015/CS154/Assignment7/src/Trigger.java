
class Trigger {
    //pair of ints
    private int currentState;
    private char currentSymbol;
    
    public Trigger(int x, char y) {
        currentState = x; currentSymbol = y;
    }

    /**
     * @return the currentState
     */
    public int getCurrentState() {
        return currentState;
    }

    /**
     * @return the currentSymbol
     */
    public char getCurrentSymbol() {
        return currentSymbol;
    }
    
}
