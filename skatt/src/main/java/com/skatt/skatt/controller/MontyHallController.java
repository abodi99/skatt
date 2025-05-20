package com.skatt.skatt.controller;

import com.skatt.skatt.service.MontyHallService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MontyHallController {
    private final MontyHallService montyHallService;

    public MontyHallController(MontyHallService montyHallService) {
        this.montyHallService = montyHallService;
    }

    @GetMapping("/simulate")
    public ResponseEntity<MontyHallService.SimulationResult> simulate(
            @RequestParam(defaultValue = "10000") int numberOfGames) {
        return ResponseEntity.ok(montyHallService.runSimulation(numberOfGames));
    }
}
