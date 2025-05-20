package com.skatt.skatt.model;

public class MontyHallGame {
    private final int treasureLocation;
    private final int initialChoice;
    private final int revealedEmptyIsland;
    private boolean switched;
    private Integer finalChoice;

    public MontyHallGame(int treasureLocation, int initialChoice, int revealedEmptyIsland) {
        this.treasureLocation = treasureLocation;
        this.initialChoice = initialChoice;
        this.revealedEmptyIsland = revealedEmptyIsland;
    }

    public void switchChoice() {
        // The final choice will be the island that is neither the initial choice nor the revealed empty island
        this.finalChoice = 3 - initialChoice - revealedEmptyIsland;
        this.switched = true;
    }

    public void stayWithInitialChoice() {
        this.finalChoice = initialChoice;
        this.switched = false;
    }

    public boolean isWinner() {
        return finalChoice != null && finalChoice == treasureLocation;
    }

    public boolean hasSwitched() {
        return switched;
    }
}
