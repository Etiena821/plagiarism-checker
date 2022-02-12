package com.plagiarism_checker.service;

import com.plagiarism_checker.model.ProfessorAssistantDTO;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    void addProfessor(ProfessorAssistantDTO professorAssistantDTO);
}
