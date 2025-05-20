package com.skatt.skatt.service;

import com.skatt.skatt.model.MontyHallGame;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class MontyHallService {
    private final Random random = new Random();

    public MontyHallGame createGame() {
        int treasureLocation = random.nextInt(3);
        int initialChoice = random.nextInt(3);
        
        // Find an island to reveal that is neither the treasure location nor the initial choice
        int revealedEmptyIsland;
        do {
            revealedEmptyIsland = random.nextInt(3);
        } while (revealedEmptyIsland == treasureLocation || revealedEmptyIsland == initialChoice);

        return new MontyHallGame(treasureLocation, initialChoice, revealedEmptyIsland);
    }

    public SimulationResult runSimulation(int numberOfGames) {
        int switchWins = 0;
        int switchLosses = 0;
        int stayWins = 0;
        int stayLosses = 0;

        for (int i = 0; i < numberOfGames; i++) {
            // Run switching strategy
            MontyHallGame switchGame = createGame();
            switchGame.switchChoice();
            if (switchGame.isWinner()) {
                switchWins++;
            } else {
                switchLosses++;
            }

            // Run staying strategy
            MontyHallGame stayGame = createGame();
            stayGame.stayWithInitialChoice();
            if (stayGame.isWinner()) {
                stayWins++;
            } else {
                stayLosses++;
            }
        }

        return new SimulationResult(
            switchWins,
            switchLosses,
            stayWins,
            stayLosses
        );
    }

    public record SimulationResult(
        int switchWins,
        int switchLosses,
        int stayWins,
        int stayLosses
    ) {
        public double getSwitchWinRate() {
            return (double) switchWins / (switchWins + switchLosses);
        }

        public double getStayWinRate() {
            return (double) stayWins / (stayWins + stayLosses);
        }
    }
}
