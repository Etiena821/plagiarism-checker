package com.plagiarism_checker.service.serviceImpl;

import com.plagiarism_checker.model.BlackListedToken;
import com.plagiarism_checker.repository.BlackListedTokenRepository;
import com.plagiarism_checker.service.BlackListService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class BlackListTokenImpl implements BlackListService {

    private final BlackListedTokenRepository blackListedTokenRepository;

    @Override
    public void blackListToken(String token) {

        BlackListedToken blackListedToken = new BlackListedToken();
        blackListedToken.setToken(token);

        blackListedTokenRepository.save(blackListedToken);
    }

    @Override
    public BlackListedToken getToken(String token) {
        return blackListedTokenRepository.findByToken(token).orElse(null);
    }

}
