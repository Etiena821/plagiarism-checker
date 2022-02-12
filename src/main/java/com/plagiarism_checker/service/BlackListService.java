package com.plagiarism_checker.service;

import com.plagiarism_checker.model.BlackListedToken;


public interface BlackListService {

    void blackListToken(String  token);

    BlackListedToken getToken(String  token);
}
