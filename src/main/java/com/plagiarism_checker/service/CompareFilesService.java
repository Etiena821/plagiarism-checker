package com.plagiarism_checker.service;

import org.springframework.stereotype.Service;

@Service
public interface CompareFilesService {

    String compareFile(String file1, String file2);
}
