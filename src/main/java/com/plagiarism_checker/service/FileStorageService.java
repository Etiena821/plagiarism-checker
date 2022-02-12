package com.plagiarism_checker.service;

import com.plagiarism_checker.model.FileDB;
import com.plagiarism_checker.repository.FileDBRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class FileStorageService {

	private final FileDBRepository fileDBRepository;

	public FileDB store(MultipartFile file, String student) throws IOException {

		String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
		FileDB fileDB = new FileDB(fileName, student, file.getContentType(), file.getBytes());

		return fileDBRepository.save(fileDB);
	}

}