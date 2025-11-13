package edu.ijse.elite_driving_schoolorm.bo.custom;

import edu.ijse.elite_driving_schoolorm.bo.SuperBO;
import edu.ijse.elite_driving_schoolorm.dto.CourseDTO;

import java.util.List;
import java.util.Optional;

public interface CourseBO extends SuperBO {
    List<CourseDTO> getAllCourses() throws Exception;

    String getLastCourseId() throws Exception;

    boolean saveCourses(CourseDTO t) throws Exception;

    boolean updateCourses(CourseDTO t) throws Exception;

    boolean deleteCourses(String id) throws Exception;

    List<String> getAllCourseIds() throws Exception;

    Optional<CourseDTO> findByCourseId(String id) throws Exception;

    String generateNewCourseId();

}
