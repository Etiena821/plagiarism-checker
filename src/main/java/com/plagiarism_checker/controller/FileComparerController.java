package com.plagiarism_checker.controller;

import com.plagiarism_checker.message.ResponseMessage;
import com.plagiarism_checker.service.CompareFilesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class FileComparerController {

    private final CompareFilesService compareFilesService;

    @PostMapping("/compare-files")
    public ResponseEntity<ResponseMessage> compareFiles(@RequestParam("name1") String firstName,
                                                        @RequestParam("name2") String secondName) {
        return new ResponseEntity<>(
                new ResponseMessage(compareFilesService.compareFile(firstName, secondName)), HttpStatus.OK);
    }
}
