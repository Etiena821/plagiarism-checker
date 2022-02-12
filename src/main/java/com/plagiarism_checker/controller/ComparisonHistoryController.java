package com.plagiarism_checker.controller;

import com.plagiarism_checker.message.ResponseMessage;
import com.plagiarism_checker.model.ComparisonHistory;
import com.plagiarism_checker.service.ComparisonHistoryService;
import com.plagiarism_checker.service.CompareFilesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ComparisonHistoryController {

    private final ComparisonHistoryService comparisonHistoryService;

    private final CompareFilesService compareFilesService;

    @GetMapping("/")
    public ResponseEntity<List<ComparisonHistory>> getComparisonHistory() {

        return new ResponseEntity<>(comparisonHistoryService.getAllComparisons(), HttpStatus.OK);
    }

    @GetMapping("/comparison-history/{id}")
    public ResponseEntity<ComparisonHistory> getComparisonHistoryById(@PathVariable Long id) {

        return new ResponseEntity<>(comparisonHistoryService.getComparisonHistoryById(id), HttpStatus.OK);
    }

    @PostMapping("/comparison-history/re-run/{id}")
    public ResponseEntity<ResponseMessage> rerunComparisonHistoryResult(@PathVariable Long id) {

        ComparisonHistory historyById = comparisonHistoryService.getComparisonHistoryById(id);

        String result = compareFilesService
                .compareFile(historyById.getFile1().getStudentName(), historyById.getFile2().getStudentName());

        return new ResponseEntity<>(new ResponseMessage(result), HttpStatus.OK);
    }
}
