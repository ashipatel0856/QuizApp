package com.quizApp.controller;

import com.quizApp.dto.PlayerDTO;
import com.quizApp.service.PlayerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/players")
public class PlayerController {

    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @PostMapping
    public ResponseEntity<PlayerDTO> savePlayer(@RequestBody PlayerDTO playerDTO) {
        PlayerDTO savedPlayer = playerService.savePlayer(playerDTO);
        return new ResponseEntity<>(savedPlayer, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<PlayerDTO>> getAllPlayers() {
        return ResponseEntity.ok(playerService.getAllPlayers());
    }

    @GetMapping("/quiz")
    public ResponseEntity<List<PlayerDTO>> getPlayersByQuizId(@RequestParam String quizId) {
        return ResponseEntity.ok(playerService.getPlayersByQuizId(quizId));
    }

}
