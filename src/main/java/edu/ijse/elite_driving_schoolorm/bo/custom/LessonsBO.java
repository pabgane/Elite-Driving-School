package edu.ijse.elite_driving_schoolorm.bo.custom;

import edu.ijse.elite_driving_schoolorm.bo.SuperBO;
import edu.ijse.elite_driving_schoolorm.dto.LessonsDTO;

import java.util.List;
import java.util.Optional;

public interface LessonsBO extends SuperBO {
    List<LessonsDTO> getAllLessons() throws Exception;

    String getLastLessonId() throws Exception;

    boolean saveLessons(LessonsDTO t) throws Exception;

    boolean updateLessons(LessonsDTO t) throws Exception;

    boolean deleteLessons(String id) throws Exception;

    List<String> getAllLessonIds() throws Exception;

    Optional<LessonsDTO> findByLessonId(String id) throws Exception;

    String generateNewLessonId();
}
