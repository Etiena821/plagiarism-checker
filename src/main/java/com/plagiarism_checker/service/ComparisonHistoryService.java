package com.plagiarism_checker.service;

import com.plagiarism_checker.model.ComparisonHistory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ComparisonHistoryService {

    List<ComparisonHistory> getAllComparisons();

    ComparisonHistory getComparisonHistoryById(Long id);
}
