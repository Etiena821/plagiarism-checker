package com.plagiarism_checker.service.serviceImpl;

import com.plagiarism_checker.model.ComparisonHistory;
import com.plagiarism_checker.model.FileDB;
import com.plagiarism_checker.repository.ComparisonHistoryRepository;
import com.plagiarism_checker.repository.FileDBRepository;
import com.plagiarism_checker.service.CompareFilesService;
import info.debatty.java.stringsimilarity.Damerau;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompareFileServiceImpl implements CompareFilesService {

    private final FileDBRepository fileDBRepository;

    private final ComparisonHistoryRepository comparisonHistoryRepository;

    @Override
    public String compareFile(String file1, String file2) {

      FileDB firstFile  = fileDBRepository.findByStudentName(file1);

      FileDB secondFile  = fileDBRepository.findByStudentName(file2);

      byte[] data1 = firstFile.getData();
      byte[] data2 = secondFile.getData();

      String one = new String(data1);
      String two = new String(data2);

      Damerau damerau = new Damerau();

      var value = damerau.distance(one, two);

      String result = ((value / one.length()) * 100) + "%";

      ComparisonHistory history = new ComparisonHistory();
      history.setFile1(firstFile);
      history.setFile2(secondFile);
      history.setComparePercentage(result);

      comparisonHistoryRepository.save(history);

      return result;
    }
}
