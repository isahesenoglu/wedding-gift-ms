package com.example.weddinggift.service.impl;

import com.example.weddinggift.model.TokenPeriod;
import com.example.weddinggift.repository.TokenPeriodRepository;
import com.example.weddinggift.service.TokenPeriodService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TokenPeriodServiceImpl implements TokenPeriodService {

    private final TokenPeriodRepository tokenPeriodRepository;
    @Override
    public long getFixedRate() {
        TokenPeriod tokenPeriod = tokenPeriodRepository.findTopByOrderByInsertDateDesc();
        if (tokenPeriod != null) {
            int periodInSecond = tokenPeriod.getPeriod();
            return periodInSecond;
        }
        return 60 * 60 * 6; // 6 saat
    }
}
