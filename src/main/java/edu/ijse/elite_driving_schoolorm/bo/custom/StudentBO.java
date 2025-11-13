package edu.ijse.elite_driving_schoolorm.bo.custom;

import edu.ijse.elite_driving_schoolorm.bo.SuperBO;
import edu.ijse.elite_driving_schoolorm.dto.StudentDTO;

import java.util.List;
import java.util.Optional;

public interface StudentBO extends SuperBO {
    List<StudentDTO> getAllStudents() throws Exception;

    String getLastStudentId() throws Exception;

    boolean saveStudents(StudentDTO t) throws Exception;

    boolean updateStudents(StudentDTO t) throws Exception;

    boolean deleteStudents(String id) throws Exception;

    List<String> getAllStudentIds() throws Exception;

    Optional<StudentDTO> findByStudentId(String id) throws Exception;

    String generateNewStudentId();
}
