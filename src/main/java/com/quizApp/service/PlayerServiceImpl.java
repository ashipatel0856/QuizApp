package com.quizApp.service;

import com.quizApp.dto.PlayerDTO;
import com.quizApp.entity.Player;
import com.quizApp.repository.PlayerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlayerServiceImpl implements PlayerService {

 private final PlayerRepository playerRepository;
 private final ModelMapper modelMapper;

    public PlayerServiceImpl(PlayerRepository playerRepository, ModelMapper modelMapper) {
        this.playerRepository = playerRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public PlayerDTO savePlayer(PlayerDTO playerDTO) {
        Player player = modelMapper.map(playerDTO, Player.class);
        Player savedPlayer = playerRepository.save(player);
        return modelMapper.map(savedPlayer, PlayerDTO.class);
    }



    @Override
    public List<PlayerDTO> getAllPlayers() {
        return playerRepository.findAll()
                .stream()
                .map(player -> modelMapper.map(player, PlayerDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<PlayerDTO> getPlayersByQuizId(String quizId) {
        return playerRepository
                .findByQuizId(quizId)
                .stream()
                .map(element -> modelMapper.map(element, PlayerDTO.class))
                .collect(Collectors.toList());
    }
}
