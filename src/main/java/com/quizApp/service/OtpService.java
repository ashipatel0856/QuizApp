package com.quizApp.service;

import com.quizApp.dto.OtpRequestDTO;
import com.quizApp.dto.OtpResponseDTO;
import com.quizApp.dto.OtpVerifyDTO;
import com.quizApp.entity.Otp;
import com.quizApp.entity.Player;
import com.quizApp.repository.OtpRepository;
import com.quizApp.repository.PlayerRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
public class OtpService {

    private final PlayerRepository playerRepository;
    private final OtpRepository otpRepository;
    private final EmailService emailService;

    public OtpService(PlayerRepository playerRepository, OtpRepository otpRepository, EmailService emailService) {
        this.playerRepository = playerRepository;
        this.otpRepository = otpRepository;
        this.emailService = emailService;
    }

    public OtpResponseDTO generateOtp(OtpRequestDTO otpRequestDTO) {
        Player player = playerRepository.findByEmail(otpRequestDTO.getEmail())
                .orElseGet(() -> {
                    Player newPlayer = new Player();
                    newPlayer.setEmail(otpRequestDTO.getEmail());
                    newPlayer.setVerified(false);
                    return playerRepository.save(newPlayer);
                });

        String otpCode = String.format("%06d", new Random().nextInt(999999));
        LocalDateTime expiryTime = LocalDateTime.now().plusMinutes(1);

        Otp otp = new Otp();
        otp.setCode(otpCode);
        otp.setExpiryTime(expiryTime);
        otp.setPlayer(player);

        otpRepository.save(otp);

        // ✅ Send OTP via email
        emailService.sendOtpEmail(player.getEmail(), otpCode);

        return new OtpResponseDTO("OTP sent to your email");
    }

    public OtpResponseDTO verifyOtp(OtpVerifyDTO otpVerifyDTO) {
        Player player = playerRepository.findByEmail(otpVerifyDTO.getEmail())
                .orElseThrow(() -> new RuntimeException("Player not found"));

        Otp otp = otpRepository.findTopByPlayerOrderByExpiryTimeDesc(player)
                .orElseThrow(() -> new RuntimeException("No OTP found"));

        if (!otp.getCode().equals(otpVerifyDTO.getCode())) {
            return new OtpResponseDTO("Invalid OTP");
        }

        if (otp.getExpiryTime().isBefore(LocalDateTime.now())) {
            return new OtpResponseDTO("OTP has to be  expired");
        }

        player.setVerified(true);
        playerRepository.save(player);

        return new OtpResponseDTO("OTP verified successfully. ");
    }
}
