package com.plagiarism_checker.service.serviceImpl;

import com.plagiarism_checker.model.ProfessorAssistant;
import com.plagiarism_checker.model.ProfessorAssistantDTO;
import com.plagiarism_checker.repository.ProfessorAssistantRepo;
import com.plagiarism_checker.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final PasswordEncoder passwordEncoder;
    private final ProfessorAssistantRepo professorAssistantRepo;

    public UserServiceImpl(PasswordEncoder passwordEncoder, ProfessorAssistantRepo professorAssistantRepo) {
        this.passwordEncoder = passwordEncoder;
        this.professorAssistantRepo = professorAssistantRepo;
    }

    @Override
    public void addProfessor(ProfessorAssistantDTO professorAssistantDTO) {
        ProfessorAssistant professorAssistant = new ProfessorAssistant();
        professorAssistant.setPassword(passwordEncoder.encode(professorAssistantDTO.getPassword()));
        professorAssistant.setUsername(professorAssistantDTO.getUsername());

        professorAssistantRepo.save(professorAssistant);
    }

    public ProfessorAssistant getUserByUsername(String username){
        return professorAssistantRepo.findByUsername(username);
    }

}
