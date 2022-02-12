package com.plagiarism_checker.repository;

import com.plagiarism_checker.model.ProfessorAssistant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessorAssistantRepo extends JpaRepository <ProfessorAssistant, Long> {

    ProfessorAssistant findByUsernameAndPassword(String username, String password);
    ProfessorAssistant findByUsername(String username);
}
