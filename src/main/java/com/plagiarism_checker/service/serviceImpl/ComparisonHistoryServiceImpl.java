package com.plagiarism_checker.service.serviceImpl;

import com.plagiarism_checker.model.ComparisonHistory;
import com.plagiarism_checker.repository.ComparisonHistoryRepository;
import com.plagiarism_checker.service.ComparisonHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ComparisonHistoryServiceImpl implements ComparisonHistoryService {

    private final ComparisonHistoryRepository comparisonHistoryRepository;

    @Override
    public List<ComparisonHistory> getAllComparisons() {
        return comparisonHistoryRepository.findAll();
    }

    @Override
    public ComparisonHistory getComparisonHistoryById(Long id) {
        return comparisonHistoryRepository.findById(id).orElse(null);
    }
}
