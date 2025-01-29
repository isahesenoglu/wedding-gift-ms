package com.example.weddinggift.schedule;

import com.example.weddinggift.model.Credential;
import com.example.weddinggift.repository.CredentialRepository;
import com.example.weddinggift.repository.TokenPeriodRepository;
import com.example.weddinggift.service.impl.TokenPeriodServiceImpl;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@Data
@AllArgsConstructor
public class CredentialJob {

    private CredentialRepository credentialRepository;
    private TokenPeriodServiceImpl tokenPeriodServiceImpl;

    @Scheduled(fixedRateString = "#{tokenPeriodServiceImpl.getFixedRate()}")

    public void updateExpiredCredentials() {
        LocalDateTime now = LocalDateTime.now();
        List<Credential> credentials = credentialRepository.findByExpirationDateBefore(now);
        System.out.println("Fetched credentials: " + credentials);
        for (Credential credential : credentials) {
            if (credential.getExpirationDate() != null && credential.getExpirationDate().isBefore(now)) {
                credential.setStatus(0);
                credential.setDataStatus(0);
                credentialRepository.save(credential);
            }
        }

        System.out.println("Expired credentials updated successfully.");
    }
}
