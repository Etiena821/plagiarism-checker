package com.plagiarism_checker.repository;

import com.plagiarism_checker.model.ComparisonHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComparisonHistoryRepository extends JpaRepository <ComparisonHistory, Long> {
}
