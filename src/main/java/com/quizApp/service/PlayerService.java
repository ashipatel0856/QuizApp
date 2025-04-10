package com.quizApp.service;

import com.quizApp.dto.PlayerDTO;

import java.util.List;

public interface PlayerService {

    PlayerDTO savePlayer(PlayerDTO playerDTO);
    List<PlayerDTO> getAllPlayers();
    List<PlayerDTO> getPlayersByQuizId(String quizId);
}
