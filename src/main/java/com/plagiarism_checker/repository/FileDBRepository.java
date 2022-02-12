package com.plagiarism_checker.repository;

import com.plagiarism_checker.model.FileDB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileDBRepository extends JpaRepository<FileDB, String> {

    FileDB findByStudentName(String name);
}